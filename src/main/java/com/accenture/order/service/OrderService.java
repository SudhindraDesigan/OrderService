package com.accenture.order.service;

import com.accenture.order.model.Item;
import com.accenture.order.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final KafkaTemplate<String, Order> kafkaTemplate;
    private final String topicName;

    public OrderService(KafkaTemplate<String, Order> kafkaTemplate,
                        @Value("${order.topic.name}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    public void pushOrderItemToKafka(Order order) {
        kafkaTemplate.send(topicName, order);
    }
}
