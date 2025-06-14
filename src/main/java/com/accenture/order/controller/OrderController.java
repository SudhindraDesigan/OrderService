package com.accenture.order.controller;

import com.accenture.order.model.Order;
import com.accenture.order.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public Order getOrderDetails(@RequestBody Order order) {
        orderService.pushOrderItemToKafka(order); // Assuming the first item is the main item to push
        return order;
    }
}
