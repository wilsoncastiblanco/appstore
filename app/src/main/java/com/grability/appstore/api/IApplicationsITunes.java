package com.grability.appstore.api;

import com.grability.appstore.models.ApplicationEntry;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by wilson on 7/04/16.
 */
public interface IApplicationsITunes {
    //limit=20/json
    @GET("topfreeapplications/{limit}/{format}")
    Call<List<ApplicationEntry>> getTopFreeApplications(@Path("limit") String limit, @Path("format") String format);
}
