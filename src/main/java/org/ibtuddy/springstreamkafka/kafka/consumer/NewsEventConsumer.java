package org.ibtuddy.springstreamkafka.kafka.consumer;

import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.ibtuddy.springstreamkafka.kafka.payload.News;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

@Configuration
@Slf4j
public class NewsEventConsumer {

    @Bean
    public Consumer<Message<News>> news() {
        return message -> {
            News news = message.getPayload();
            MessageHeaders messageHeaders = message.getHeaders();
            log.info("Received message\n---\nTOPIC: {}; PARTITION: {}; OFFSET: {};\nPAYLOAD: {}\n---",
                messageHeaders.get(KafkaHeaders.RECEIVED_TOPIC, String.class),
                messageHeaders.get(KafkaHeaders.RECEIVED_PARTITION, Integer.class),
                messageHeaders.get(KafkaHeaders.OFFSET, Long.class),
                news);
        };
    }
}
