package com.smirnoff.home.platform.backup.model.debezium;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DebeziumMessage {
    @JsonProperty("payload")
    private DebeziumPayload payload;

    @JsonCreator
    public DebeziumMessage() {
    }

}
