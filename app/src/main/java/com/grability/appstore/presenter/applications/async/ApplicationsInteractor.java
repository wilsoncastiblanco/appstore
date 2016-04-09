package com.grability.appstore.presenter.applications.async;

import com.grability.appstore.api.RESTConstants;
import com.grability.appstore.models.ApplicationEntry;
import com.grability.appstore.models.database.dataServices.RealmDatabaseHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wilson on 8/04/16.
 */
public class ApplicationsInteractor {
    private IApplicationsListener listener;

    public ApplicationsInteractor(IApplicationsListener listener){
        this.listener = listener;
    }

    public void loadApplicationList(){
        Call<List<ApplicationEntry>> call = RESTConstants.getITunesApplicationsEndpoint().getTopFreeApplications(RESTConstants.LIMIT, RESTConstants.JSON_FORMAT);
        call.enqueue(new Callback<List<ApplicationEntry>>() {
            @Override
            public void onResponse(Call<List<ApplicationEntry>> call, Response<List<ApplicationEntry>> response) {
                boolean responseRealm = RealmDatabaseHelper.addApplicationList(response.body());
                if(responseRealm){
                    listener.OnRequestSuccess();
                }else {
                    listener.OnRequestFailed();
                }
            }

            @Override
            public void onFailure(Call<List<ApplicationEntry>> call, Throwable t) {
                listener.OnRequestFailed();
            }
        });
    }
}
