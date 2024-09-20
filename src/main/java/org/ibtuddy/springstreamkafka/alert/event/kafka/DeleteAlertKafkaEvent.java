package org.ibtuddy.springstreamkafka.alert.event.kafka;

public record DeleteAlertKafkaEvent(String id, Integer level, String message) {
}

