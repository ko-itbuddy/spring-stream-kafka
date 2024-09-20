package org.ibtuddy.springstreamkafka.alert.event.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AlertEventProducer {

    private final StreamBridge streamBridge;

    @Value("${spring.cloud.stream.bindings.createAlertKafkaEvent-out-0.destination}")
    private String createAlertKafkaEventTopic;

    public void send(CreateAlertKafkaEvent createAlertKafkaEvent) {
        log.info("Sending Alert '{}' to topic '{}'", createAlertKafkaEvent, createAlertKafkaEventTopic);

        Message<CreateAlertKafkaEvent> message = MessageBuilder.withPayload(createAlertKafkaEvent)
                                                               .setHeader("partitionKey", createAlertKafkaEvent.id())
                                                               .build();
        streamBridge.send("createAlertKafkaEvent-out-0", message);
    }

    @Value("${spring.cloud.stream.bindings.deleteAlertKafkaEvent-out-0.destination}")
    private String deleteAlertKafkaEventTopic;

    public void send(DeleteAlertKafkaEvent deleteAlertKafkaEvent) {
        log.info("Sending Alert '{}' to topic '{}'", deleteAlertKafkaEvent, deleteAlertKafkaEventTopic);

        Message<DeleteAlertKafkaEvent> message = MessageBuilder.withPayload(deleteAlertKafkaEvent)
                                                               .setHeader("partitionKey", deleteAlertKafkaEvent.id())
                                                               .build();
        streamBridge.send("deleteAlertKafkaEvent-out-0", message);
    }
}
