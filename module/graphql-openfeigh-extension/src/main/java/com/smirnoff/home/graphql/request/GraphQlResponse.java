package com.smirnoff.home.graphql.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GraphQlResponse<T> {

    @JsonProperty("data")
    private final T data;

    @JsonCreator
    public GraphQlResponse(T data) {
        this.data = data;
    }

    public static class VoidGraphQlResponse extends GraphQlResponse<Void> {

        @JsonCreator
        public VoidGraphQlResponse(Void data) {
            super(data);
        }
    }
}
