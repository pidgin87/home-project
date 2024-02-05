package com.smirnoff.home.garden.iot.job.route.device.status;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.smirnoff.home.garden.iot.job.util.CamelUtils.singletonJsonObject;

@Component
public class UpdateDevicesRoute extends RouteBuilder {

    @Value("${home-garden.endpoints.device-graphql}")
    private String deviceServiceEndpoint;
    @Override
    public void configure() throws Exception {
        from("direct:refresh-devices")
                .routeId("refresh-devices-status")
                .process(exchange -> {
                    String deviceId = exchange.getIn().getBody(String.class);
                    exchange.getIn().setBody(singletonJsonObject("deviceId", deviceId));
                })
                .to("graphql:" + deviceServiceEndpoint + "?queryFile=devicesQuery.graphql&operationName=GetDeviceStatusInfo")
                .to("log: ${body}")
        ;
    }
}
