package com.smirnoff.home.platform.backup.camel.listener;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.smirnoff.home.platform.backup.camel.listener.processor.ExceptionMessageHeaderWriterProcessor;
import com.smirnoff.home.platform.backup.mapper.BackupEntityModelMapper;
import com.smirnoff.home.platform.backup.model.BackupEntityModel;
import com.smirnoff.home.platform.backup.model.debezium.DebeziumMessage;
import com.smirnoff.home.platform.backup.model.debezium.DebeziumPayload;
import com.smirnoff.home.platform.backup.model.debezium.DebeziumPayloadSource;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class BackupKafkaListener extends RouteBuilder {

    @Value("${home-project.backup-service.directory.path}")
    private String backupPath;

    private final BackupEntityModelMapper backupEntityModelMapper;

    @Override
    public void configure() throws Exception {
        JacksonDataFormat formatter = new JacksonDataFormat(DebeziumMessage.class);
        formatter.disableFeature(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        from("""
                    kafka:{{home-project.backup-service.kafka.consumer.topics}}?
                        brokers={{home-project.backup-service.kafka.bootstraps}}&
                        groupId={{home-project.backup-service.kafka.consumer.group-id}}
                """)
                .routeId("read-backups-messages")
                .errorHandler(deadLetterChannel("""
                            kafka:backup-service.DLT?brokers={{home-project.backup-service.kafka.bootstraps}}
                        """).onExceptionOccurred(new ExceptionMessageHeaderWriterProcessor()))
                .process(this::setDltTopic)
                .unmarshal(formatter)
                .process(this::setOperationHeaders)
                .process(exchange -> {
                    BackupEntityModel model = backupEntityModelMapper.map(exchange.getIn().getBody(DebeziumMessage.class));
                    exchange.getIn().setBody(model);
                })
                .choice().when(header("operation").isEqualTo("d"))//when operation is delete
                .process(exchange -> {
                    exchange.getIn().getBody(BackupEntityModel.class).setDeleted(Boolean.TRUE);
                })
                .end()
                .marshal(formatter)
                .to("file:" + backupPath + "?fileName=${in.header.backupSchema}/${in.header.backupTable}/${in.header.primaryKey}.json")
                .log("entity: [${in.header.backupTable}] from schema: [${in.header.backupSchema}] with primary key: [${in.header.primaryKey}] was changed")
                .end();
    }

    private void setDltTopic(Exchange exchange) {
        String fromTopic = exchange.getIn().getHeader("kafka.TOPIC", String.class);
        exchange.getIn().setHeader("kafka.OVERRIDE_TOPIC", fromTopic + ".DLT");
    }

    private void setOperationHeaders(Exchange message) {
        DebeziumMessage debeziumMessage = (DebeziumMessage) message.getIn().getBody();
        message.getIn().setHeader("operation", debeziumMessage.getPayload().getOp());

        DebeziumPayloadSource source = debeziumMessage.getPayload().getSource();
        message.getIn().setHeader("backupSchema", source.getSchema());
        message.getIn().setHeader("backupTable", source.getTable());
        message.getIn().setHeader("primaryKey", findPrimaryKey(debeziumMessage.getPayload()));
    }

    private String findPrimaryKey(DebeziumPayload payload) {
        Map<String, String> after = payload.getAfter();
        if (nonNull(after)) {
            return after.get("id");
        }
        Map<String, String> before = payload.getBefore();
        if (nonNull(before)) {
            return before.get("id");
        }
        throw new IllegalArgumentException("can't find primary key of entity");
    }
}
