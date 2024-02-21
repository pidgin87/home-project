package com.smirnoff.home.garden.iot.device.camel;

import com.smirnoff.home.garden.iot.device.persistance.model.DeviceEntity;
import com.smirnoff.home.garden.iot.device.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.smirnoff.home.garden.iot.device.util.CamelUtils.singletonJsonObject;
import static com.smirnoff.home.garden.iot.device.util.CamelUtils.singletonJsonObjects;

@Component
@RequiredArgsConstructor
public class GetDevicesStatusesRoute extends RouteBuilder {

    public static final String GET_DEVICES_STATUSES = "direct:get-devices-statuses";

    @Value("${home-garden.endpoints.device-command-graphql}")
    private String deviceCommandServiceEndpoint;

    private final DeviceService deviceService;
    @Override
    public void configure() throws Exception {
        from(GET_DEVICES_STATUSES)
                .routeId("get-devices-statuses")
                .process(exchange -> {
                    List<String> remoteUids = exchange.getIn().getBody(List.class);
                    exchange.getIn().setBody(singletonJsonObjects("remoteUids", remoteUids));
                })
                .to("graphql:" + deviceCommandServiceEndpoint + "?queryFile=devicesCommandQuery.graphql&operationName=getRemoteDevices")
                .to("log:rooms");
    }
}
