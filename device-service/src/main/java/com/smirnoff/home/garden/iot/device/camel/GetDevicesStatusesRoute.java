package com.smirnoff.home.garden.iot.device.camel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirnoff.home.garden.iot.device.model.Device;
import com.smirnoff.home.garden.iot.device.persistance.model.DeviceEntity;
import com.smirnoff.home.garden.iot.device.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.smirnoff.home.garden.iot.device.util.CamelUtils.singletonJsonObject;
import static com.smirnoff.home.garden.iot.device.util.CamelUtils.singletonJsonObjects;
import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class GetDevicesStatusesRoute extends RouteBuilder {

    public static final String GET_DEVICES_STATUSES = "direct:get-devices-statuses";

    @Value("${home-garden.endpoints.device-command-graphql}")
    private String deviceCommandServiceEndpoint;

    private final ObjectMapper objectMapper;

    @Override
    public void configure() throws Exception {
        JacksonDataFormat dataFormat = new JacksonDataFormat(Device.class);
        dataFormat.setObjectMapper(objectMapper);

        from(GET_DEVICES_STATUSES)
                .routeId("get-devices-statuses")
                .process(exchange -> {
                    List<String> remoteUids = exchange.getIn().getBody(List.class);
                    exchange.getIn().setBody(singletonJsonObjects("remoteUids", remoteUids));
                })
                .to("graphql:" + deviceCommandServiceEndpoint + "?queryFile=devicesCommandQuery.graphql&operationName=GetDevicesStatuses")
                .split().jsonpath("$.data.getRemoteDevices")
                    .aggregationStrategy(new JoinDeviceAggregationStrategy())
                    .marshal().json()
                    .convertBodyTo(String.class)
                    .unmarshal(dataFormat)
                .end();
    }

    private static class JoinDeviceAggregationStrategy implements AggregationStrategy {
        @Override
        public Exchange aggregate(Exchange previous, Exchange current) {
            if (isNull(previous)) {
                List<Device> newList = buildListFrom(current);
                current.getMessage().setBody(newList, List.class);
                return current;
            }
            List<Device> previousDevices = previous.getMessage().getBody(List.class);
            List<Device> newList = buildListFrom(current);

            List<Device> combined = Stream.concat(previousDevices.stream(), newList.stream()).collect(Collectors.toList());
            previous.getMessage().setBody(combined);
            return previous;
        }

        private List<Device> buildListFrom(Exchange exchange) {
            Device object = exchange.getMessage().getBody(Device.class);
            List<Device> list = new ArrayList<>();
            list.add(object);
            return list;
        }
    }
}
