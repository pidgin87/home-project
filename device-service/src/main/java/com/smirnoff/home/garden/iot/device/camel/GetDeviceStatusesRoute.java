package com.smirnoff.home.garden.iot.device.camel;

import com.smirnoff.home.garden.iot.device.persistance.model.DeviceEntity;
import com.smirnoff.home.garden.iot.device.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.smirnoff.home.garden.iot.device.util.CamelUtils.singletonJsonObject;

@Component
@RequiredArgsConstructor
public class GetDeviceStatusesRoute extends RouteBuilder {

    @Value("${home-garden.endpoints.device-command-graphql}")
    private String deviceCommandServiceEndpoint;

    private final DeviceService deviceService;
    @Override
    public void configure() throws Exception {
        from("direct:get-device-statuses")
                .routeId("get-device-statuses")
                .process(exchange -> {
                    String deviceId = exchange.getIn().getBody(String.class);
                    DeviceEntity device = deviceService.findById(deviceId).orElseThrow();
                    exchange.getIn().setBody(singletonJsonObject("remoteUid", device.getGlobalId()));
                })
                .to("graphql:" + deviceCommandServiceEndpoint + "?queryFile=devicesCommandQuery.graphql&operationName=GetDeviceStatuses")
                .to("log:rooms");
    }
}
