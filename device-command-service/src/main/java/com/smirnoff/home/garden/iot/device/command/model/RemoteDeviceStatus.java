package com.smirnoff.home.garden.iot.device.command.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class RemoteDeviceStatus {
    private RemoteDeviceStatusProperty property;
    private String code;
    private Object value;

    public enum RemoteDeviceStatusProperty {
        HUMIDITY(Arrays.asList("humidity")),
        TEMPERATURE(Arrays.asList("temp_current")),
        BATTERY_PERCENTAGE(Arrays.asList("battery_percentage")),
        UNDEFINED(Collections.emptyList()),
        ;

        private final List<String> synonyms;

        RemoteDeviceStatusProperty(List<String> synonyms) {
            this.synonyms = synonyms;
        }

        public static RemoteDeviceStatusProperty findByCode(String code) {
            for (RemoteDeviceStatusProperty value : RemoteDeviceStatusProperty.values()) {
                if (value.synonyms.contains(code)) {
                    return value;
                }
            }
            return UNDEFINED;
        }
    }
}
