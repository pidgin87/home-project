package com.smirnoff.home.graphql.request;

import java.util.HashMap;
import java.util.function.Function;

import static java.util.Objects.isNull;

public class GraphQlVariables extends HashMap<String, Object> {

    public <T> GraphQlVariables addObject(String key, Object value) {
        if (isNull(value)) {
            return this;
        }
        put(key, value);
        return this;
    }

    public <T> GraphQlVariables addObject(String key, T value, Function<T, Object> getter) {
        if (isNull(value)) {
            return this;
        }
        put(key, getter.apply(value));
        return this;
    }
}
