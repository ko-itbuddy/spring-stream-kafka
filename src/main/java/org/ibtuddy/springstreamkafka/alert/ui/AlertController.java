package org.ibtuddy.springstreamkafka.alert.ui;

import jakarta.validation.Valid;
import org.ibtuddy.springstreamkafka.alert.event.kafka.CreateAlertKafkaEvent;
import org.ibtuddy.springstreamkafka.alert.event.kafka.DeleteAlertKafkaEvent;
import org.ibtuddy.springstreamkafka.alert.event.kafka.AlertEventProducer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public Mono<CreateAlertKafkaEvent> publishCreateAlertKafkaEvent(@Valid @RequestBody CreateAlertRequest createAlertRequest) {
        CreateAlertKafkaEvent createAlertKafkaEvent = new CreateAlertKafkaEvent(
            UUID.randomUUID().toString(), createAlertRequest.level(), createAlertRequest.message());
        alertEventProducer.send(createAlertKafkaEvent);
        return Mono.just(createAlertKafkaEvent);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public Mono<DeleteAlertKafkaEvent> publishDeleteAlertKafkaEvent(@Valid @RequestBody CreateAlertRequest createAlertRequest) {
        DeleteAlertKafkaEvent deleteAlertKafkaEvent = new DeleteAlertKafkaEvent(
            UUID.randomUUID().toString(), createAlertRequest.level(), createAlertRequest.message());
        alertEventProducer.send(deleteAlertKafkaEvent);
        return Mono.just(deleteAlertKafkaEvent);
    }
}
