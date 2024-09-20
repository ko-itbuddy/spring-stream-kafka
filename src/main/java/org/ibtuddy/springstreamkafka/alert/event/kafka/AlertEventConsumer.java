package org.ibtuddy.springstreamkafka.alert.event.kafka;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

@Configuration
@Slf4j
public class AlertEventConsumer {

    @Bean
    public Consumer<Message<CreateAlertKafkaEvent>> createAlertKafkaEvent() {
        return message -> {
            CreateAlertKafkaEvent deleteAlert = message.getPayload();
            MessageHeaders messageHeaders = message.getHeaders();
            log.info("Received message\n---\nTOPIC: {}; PARTITION: {}; OFFSET: {};\nPAYLOAD: {}\n---",
                messageHeaders.get(KafkaHeaders.RECEIVED_TOPIC, String.class),
                messageHeaders.get(KafkaHeaders.RECEIVED_PARTITION, Integer.class),
                messageHeaders.get(KafkaHeaders.OFFSET, Long.class),
                deleteAlert);
        };
    }

    @Bean
    public Consumer<Message<DeleteAlertKafkaEvent>> deleteAlertKafkaEvent() {
        return message -> {
            DeleteAlertKafkaEvent deleteAlert = message.getPayload();
            MessageHeaders messageHeaders = message.getHeaders();
            Timestamp timestamp = messageHeaders.get(KafkaHeaders.TIMESTAMP, Timestamp.class);
            log.info("Received message\n---\nTOPIC: {}; PARTITION: {}; OFFSET: {};\nPAYLOAD: {}\n---",
                messageHeaders.get(KafkaHeaders.RECEIVED_TOPIC, String.class),
                messageHeaders.get(KafkaHeaders.RECEIVED_PARTITION, Integer.class),
                messageHeaders.get(KafkaHeaders.OFFSET, Long.class),
                deleteAlert);
        };
    }

}
