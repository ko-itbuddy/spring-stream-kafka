package org.ibtuddy.springstreamkafka.kafka.payload;

public record Alert(String id, Integer level, String message) {
}

