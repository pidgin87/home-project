package com.smirnoff.home.platform.backup.model.debezium;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DebeziumPayloadSource {
    @JsonProperty("db")
    private String db;

    @JsonProperty("schema")
    private String schema;

    @JsonProperty("table")
    private String table;
}
