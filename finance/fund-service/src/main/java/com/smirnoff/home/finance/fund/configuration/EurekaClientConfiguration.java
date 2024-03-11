package com.smirnoff.home.finance.fund.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class EurekaClientConfiguration {

    @Value("${eureka.client.interface:eth0}")
    private String interfaceName;

    @Value("${server.port}")
    private Integer serverPort;

    private final InetUtils inetUtils;

    @Bean
    public EurekaInstanceConfigBean eurekaInstanceConfigBean() throws SocketException {
        EurekaInstanceConfigBean eurekaInstanceConfig = new EurekaInstanceConfigBean(inetUtils);

        NetworkInterface networkInterface = NetworkInterface.getByName(interfaceName);

        Enumeration<InetAddress> inetAddress = networkInterface.getInetAddresses();
        InetAddress current = inetAddress.nextElement();

        log.error("network instance name: " + networkInterface.getName());

        final String hostName = System.getenv("HOSTNAME");
        log.error("hostName : " + hostName);

        String address = current.toString().split("/")[1];
        log.error("address : " + address);

        eurekaInstanceConfig.setHostname(hostName);
        eurekaInstanceConfig.setPreferIpAddress(true);
        eurekaInstanceConfig.setIpAddress(address);
        eurekaInstanceConfig.setNonSecurePort(serverPort);

        return eurekaInstanceConfig;
    }
}
