package org.ibtuddy.springstreamkafka.news.event.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NewsEventProducer {

    private final StreamBridge streamBridge;

    @Value("${spring.cloud.stream.bindings.news-out-0.destination}")
    private String kafkaTopic;

    public void send(News news) {
        log.info("Sending News '{}' to topic '{}'", news, kafkaTopic);

        Message<News> message = MessageBuilder.withPayload(news)
                                              .setHeader(KafkaHeaders.KEY, news.id())
                                              .build();
        streamBridge.send("news-out-0", message);
    }
}

