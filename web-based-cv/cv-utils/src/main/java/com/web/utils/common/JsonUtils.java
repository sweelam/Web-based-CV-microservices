package com.web.utils.common;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;

public class JsonUtils {
    private JsonUtils() {}
    private final static Gson gson = new Gson();
    private final static JsonParser jsonParser = new JsonParser();

    public static JsonElement parse(String val) {
        return !StringUtils.isEmpty(val) ?
                jsonParser.parse(val) : new JsonObject();
    }

    public static JsonElement toJson(Object o) {
        return null != o ? gson.toJsonTree(o) : new JsonObject();
    }

    public static <T> T toObject(JsonElement o, Class<T> type) {
        return gson.fromJson(o, type);
    }
}
