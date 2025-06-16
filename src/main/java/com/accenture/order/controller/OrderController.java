package com.accenture.order.controller;

import com.accenture.order.model.Order;
import com.accenture.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<?> getOrderDetails(@RequestBody List<Order> orderList) {
        orderService.pushOrderItemToKafka(orderList).subscribe();
        return ResponseEntity.status(201).body(
                "Order items sent for Processing");
    }
}
