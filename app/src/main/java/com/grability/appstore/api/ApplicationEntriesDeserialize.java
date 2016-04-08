package com.grability.appstore.api;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.grability.appstore.models.ApplicationEntry;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by wilson on 7/04/16.
 */
public class ApplicationEntriesDeserialize implements JsonDeserializer<List<ApplicationEntry>> {
    @Override
    public List<ApplicationEntry> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jdc)
            throws JsonParseException
    {
        JsonElement content = jsonElement.getAsJsonObject().get(RESTConstants.FEED_OBJECT).getAsJsonObject().get(RESTConstants.ENTRY_ARRAY);
        return new Gson().fromJson(content, new TypeToken<List<ApplicationEntry>>(){}.getType());

    }
}
