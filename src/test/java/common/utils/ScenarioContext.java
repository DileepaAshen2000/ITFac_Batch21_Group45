package common.utils;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private final Map<String, Object> data = new HashMap<>();

    public void set(String key, Object value) { data.put(key, value); }
    public <T> T get(String key, Class<T> clazz) { return clazz.cast(data.get(key)); }

    public void setResponse(Response response) { set("response", response); }
    public Response getResponse() { return get("response", Response.class); }
}
