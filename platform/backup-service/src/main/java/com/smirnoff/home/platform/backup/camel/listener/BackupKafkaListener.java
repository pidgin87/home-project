package com.smirnoff.home.platform.backup.camel.listener;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.smirnoff.home.platform.backup.mapper.BackupEntityModelMapper;
import com.smirnoff.home.platform.backup.model.BackupEntityModel;
import com.smirnoff.home.platform.backup.model.debezium.DebeziumMessage;
import com.smirnoff.home.platform.backup.model.debezium.DebeziumPayloadSource;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BackupKafkaListener extends RouteBuilder {

    @Value("${home-project.backup-service.camel-kafka-uri}")
    private String camelKafkaUri;

    @Value("${home-project.backup-service.directory.path}")
    private String backupPath;

    private final BackupEntityModelMapper backupEntityModelMapper;

    @Override
    public void configure() throws Exception {
        JacksonDataFormat formatter = new JacksonDataFormat(DebeziumMessage.class);
        formatter.disableFeature(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        from("kafka:" + camelKafkaUri)
                .routeId("read-backups-messages")
                .unmarshal(formatter)
                .process(this::setOperationHeaders)
                .process(exchange -> {
                    BackupEntityModel model = backupEntityModelMapper.map(exchange.getIn().getBody(DebeziumMessage.class));
                    exchange.getIn().setBody(model);
                })
                .marshal(formatter)
                .to("file:" + backupPath + "?fileName=${in.header.backupSchema}/${in.header.backupTable}/${in.header.primaryKey}.json")
                .end();
    }

    private void setOperationHeaders(Exchange message) {
        DebeziumMessage debeziumMessage = (DebeziumMessage) message.getIn().getBody();
        message.getIn().setHeader("backupOperation", debeziumMessage.getPayload().getOp());

        DebeziumPayloadSource source = debeziumMessage.getPayload().getSource();
        message.getIn().setHeader("backupSchema", source.getSchema());
        message.getIn().setHeader("backupTable", source.getTable());

        message.getIn().setHeader("primaryKey", debeziumMessage.getPayload().getAfter().get("id"));
    }
}
