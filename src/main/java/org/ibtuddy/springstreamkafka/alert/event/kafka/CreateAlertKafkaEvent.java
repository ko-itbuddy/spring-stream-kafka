package org.ibtuddy.springstreamkafka.alert.event.kafka;

public record CreateAlertKafkaEvent(String id, Integer level, String message) {
}

