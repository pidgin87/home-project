package com.smirnoff.home.garden.iot.device.command;

import com.tuya.connector.spring.annotations.ConnectorScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

@ConnectorScan(basePackages = "com.smirnoff.home.garden.iot.device.command.adapter")
@EnableCaching
@SpringBootApplication
@ComponentScan(value = {"com.smirnoff.home.platform", "com.smirnoff.home.garden.iot.device.command"})
public class DeviceCommandServiceRunner {
    public static void main(String[] args) {
        SpringApplication.run(DeviceCommandServiceRunner.class, args);
    }

//    @EventListener
//    private void whenApplicationStarted(ApplicationStartedEvent event) throws IOException {
//        Socket socket = new Socket("192.168.68.105", 6668);
//
//
//
//        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
//        output.write(1);
//        output.flush();
//
//        byte[] bytes = socket.getInputStream().readNBytes(128);
//        System.out.println(new String(bytes));
//    }
}
