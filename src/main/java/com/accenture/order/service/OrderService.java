package com.accenture.order.service;

import com.accenture.order.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class OrderService {

    private final ReactiveKafkaProducerTemplate<String, Order> reactiveKafkaProducerTemplate;
    private final String topicName;

    public OrderService(KafkaProperties properties,
                        @Value("${order.topic}") String topicName, ReactiveKafkaProducerTemplate<String, Order> reactiveKafkaProducerTemplate) {
        this.reactiveKafkaProducerTemplate = reactiveKafkaProducerTemplate;
        this.topicName = topicName;
    }

    public Mono<Void> pushOrderItemToKafka(List<Order> orderList) {
        log.info("Pushing order items to Kafka topic: {}", topicName);
        return Flux.fromIterable(orderList)
                .flatMap(order -> reactiveKafkaProducerTemplate.send(topicName, order.getOrderId(), order)
                          .doOnSuccess(result -> log.info("Message sent successfully for Order ID: {}", order.getOrderId()))
                        .doOnError(error -> log.error("Failed to send message for Order ID: {}", order.getOrderId(), error))
                       )
                .then();
    }
}
