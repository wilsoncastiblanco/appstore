package com.grability.appstore.presenter.categories.async;

import android.util.Log;

import com.grability.appstore.api.RESTConstants;
import com.grability.appstore.models.ApplicationEntry;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wilson on 7/04/16.
 */
public class CategoriesInteractor {
    private ICategoriesListener listener;

    public CategoriesInteractor(ICategoriesListener listener){
        this.listener = listener;
    }

    public void loadCategoriesList(){
        Call<List<ApplicationEntry>> call = RESTConstants.getITunesApplicationsEndpoint().getTopFreeApplications(RESTConstants.LIMIT, RESTConstants.JSON_FORMAT);
        call.enqueue(new Callback<List<ApplicationEntry>>() {
            @Override
            public void onResponse(Call<List<ApplicationEntry>> call, Response<List<ApplicationEntry>> response) {
                Log.i("Response", response.toString());
                Log.i("Response", response.body().toString());

                //listener.OnRequestSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<ApplicationEntry>> call, Throwable t) {
                listener.OnRequestFailed();
            }
        });
    }

}
