package com.smirnoff.home.garden.iot.job.route.device.status;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.smirnoff.home.garden.iot.job.util.CamelUtils.singletonJsonObject;

@Component
public class FindDevicesTimerRoute extends RouteBuilder {

    @Value("${home-garden.endpoints.room-graphql}")
    private String roomServiceEndpoint;

    @Value("${home-garden.endpoints.device-graphql}")
    private String deviceServiceEndpoint;

    @Override
    public void configure() throws Exception {
        from("timer://room-update-timer?period=10000")
                .routeId("find-devices")
                    .to("graphql:" + roomServiceEndpoint + "?queryFile=roomsQuery.graphql&operationName=GetAllRooms")
                    .split().jsonpath("$.data.rooms..id")
                        .process(exchange -> {
                            String roomId = exchange.getIn().getBody(String.class);
                            exchange.getIn().setBody(singletonJsonObject("roomId", roomId));
                        })
                        .to("graphql:" + deviceServiceEndpoint + "?queryFile=devicesQuery.graphql&operationName=GetDeviceListByRoom")
                        .to("direct:update-room-statuses")
                .end()
        ;
    }


}
