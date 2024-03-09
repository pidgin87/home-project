package com.smirnoff.home.platform.backup.model.debezium;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DebeziumPayload {

    @JsonProperty("payload")
    private Map<String, String> before;

    @JsonProperty("after")
    private Map<String, String> after;

    @JsonProperty("source")
    private DebeziumPayloadSource source;

    @JsonProperty("op")
    private String op;

    @JsonCreator
    public DebeziumPayload() {
    }
}
