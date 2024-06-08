package com.smirnoff.home.finance.rate.procedure.integration.configuration;

import com.smirnoff.home.finance.rate.procedure.persist.model.StockRateEntity;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.jpa.core.JpaExecutor;
import org.springframework.integration.jpa.outbound.JpaOutboundGateway;
import org.springframework.integration.jpa.support.PersistMode;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
@RequiredArgsConstructor
public class StockRateEntityJpaIntegrationConfiguration {

    private final EntityManagerFactory entityManagerFactory;

    @Bean("persist.rate.stock.channel")
    public MessageChannel persistOut() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "persist.rate.stock.channel")
    public MessageHandler jpaOutbound() {
        JpaOutboundGateway gateway = new JpaOutboundGateway(stockRateEntityJpaExecutor());
        gateway.setProducesReply(false);
        return gateway;
    }

    private JpaExecutor stockRateEntityJpaExecutor() {
        JpaExecutor jpaExecutor = new JpaExecutor(this.entityManagerFactory);
        jpaExecutor.setEntityClass(StockRateEntity.class);
        jpaExecutor.setPersistMode(PersistMode.PERSIST);
        return jpaExecutor;
    }
}
