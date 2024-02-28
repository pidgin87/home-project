package com.smirnoff.home.garden.iot.room.camel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirnoff.home.garden.iot.room.model.Device;
import com.smirnoff.home.garden.iot.room.util.CamelUtils;
import lombok.RequiredArgsConstructor;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.camel.component.jackson.JacksonDataFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class GetDevicesByRoomRoute extends RouteBuilder {

    public static final String GET_DEVICES_BY_ROOMS = "direct:get-devices-by-rooms";

    @Value("${home-garden.endpoints.device-graphql}")
    private String deviceServiceEndpoint;

    private final ObjectMapper objectMapper;

    @Override
    public void configure() throws Exception {
        JacksonDataFormat dataFormat = new JacksonDataFormat(Device.class);
        dataFormat.setObjectMapper(objectMapper);

        from(GET_DEVICES_BY_ROOMS)
                .routeId("get-devices-by-rooms")
                .process(exchange -> {
                    List<String> roomIds = exchange.getIn().getBody(List.class);
                    exchange.getIn().setBody(CamelUtils.singletonJsonObjects("roomIds", roomIds));
                })
                .to("graphql:" + deviceServiceEndpoint + "?queryFile=devicesQuery.graphql&operationName=GetDeviceListByRooms")
                .split().jsonpath("$.data.getDevicesByRooms")
                    .aggregationStrategy(new JoinDeviceAggregationStrategy())
                    .marshal().json()
                    .convertBodyTo(String.class)
                    .unmarshal(dataFormat)
                .end()
        ;
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
