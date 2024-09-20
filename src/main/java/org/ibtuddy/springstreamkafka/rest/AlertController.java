package org.ibtuddy.springstreamkafka.rest;

import jakarta.validation.Valid;
import org.ibtuddy.springstreamkafka.kafka.payload.Alert;
import org.ibtuddy.springstreamkafka.kafka.producer.AlertEventProducer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    private final AlertEventProducer alertEventProducer;

    public AlertController(AlertEventProducer alertEventProducer) {
        this.alertEventProducer = alertEventProducer;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Mono<Alert> publish(@Valid @RequestBody CreateAlertRequest createAlertRequest) {
        Alert alert = new Alert(
            UUID.randomUUID().toString(), createAlertRequest.level(), createAlertRequest.message());
        alertEventProducer.send(alert);
        return Mono.just(alert);
    }
}
