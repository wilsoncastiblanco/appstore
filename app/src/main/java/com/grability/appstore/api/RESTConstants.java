package com.grability.appstore.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.grability.appstore.models.ApplicationEntry;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wilson on 3/03/16.
 */
public class RESTConstants {
    public static String BASE_URL = "https://itunes.apple.com/us/rss/";
    public static String LIMIT = "limit=20";
    public static String JSON_FORMAT = "json";

    /*API attributes*/
    public static String FEED_OBJECT = "feed";
    public static String ENTRY_ARRAY = "entry";

    static RESTConstants instance;
    static Retrofit retrofit;

    public static void init() {
        if (instance == null) {
            instance = new RESTConstants();
        }
    }

    public RESTConstants(){
        Gson gson =
                new GsonBuilder()
                        .registerTypeAdapter(new TypeToken<List<ApplicationEntry>>(){}.getType(), new ApplicationEntriesDeserialize())
                        .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static IApplicationsITunes getITunesApplicationsEndpoint(){
        return retrofit.create(IApplicationsITunes.class);
    }
}
