package com.smirnoff.home.garden.iot.device.util;

import org.apache.camel.util.json.JsonObject;

import java.util.List;

public class CamelUtils {

    public static JsonObject singletonJsonObjects(String name, List<String> values) {
        JsonObject variables = new JsonObject();
        variables.put(name, values);
        return variables;
    }

    public static JsonObject singletonJsonObject(String name, String value) {
        JsonObject variables = new JsonObject();
        variables.put(name, value);
        return variables;
    }
}
