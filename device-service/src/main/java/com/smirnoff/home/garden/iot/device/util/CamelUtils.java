package com.smirnoff.home.garden.iot.device.util;

import org.apache.camel.util.json.JsonObject;

public class CamelUtils {
    public static JsonObject singletonJsonObject(String name, String value) {
        JsonObject variables = new JsonObject();
        variables.put(name, value);
        return variables;
    }
}
