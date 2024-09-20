package org.ibtuddy.springstreamkafka.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.ibtuddy.springstreamkafka.kafka.payload.News;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j

public class NewsEventProducer {

    private final StreamBridge streamBridge;

    public NewsEventProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Value("${spring.cloud.stream.bindings.news-out-0.destination}")
    private String kafkaTopic;

    public void send(News news) {
        log.info("Sending News '{}' to topic '{}'", news, kafkaTopic);

        Message<News> message = MessageBuilder.withPayload(news)
                                              .setHeader("partitionKey", news.id())
                                              .build();
        streamBridge.send("news-out-0", message);
    }
}

