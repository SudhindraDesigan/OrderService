package com.accenture.order.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class Order {

    @NotNull(message = "Order ID cannot be null")
    @Size(min = 5, max = 20, message = "Order ID must be between 5 and 20 characters")
    private String orderId;

    @NotNull(message = "Customer name cannot be null")
    @Size(min = 2, max = 50, message = "Customer name must be between 2 and 50 characters")
    private String customerName;

    @NotNull(message = "Order date cannot be null")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Order date must be in the format YYYY-MM-DD")
    private String orderDate;

    @NotNull(message = "Items cannot be null")
    @Size(min = 1, message = "Order must contain at least one item")
    private List<@Valid Item> items;

    @NotNull(message = "Total amount cannot be null")
    @Pattern(regexp = "\\d+(\\.\\d{1,2})?", message = "Total amount must be a valid monetary value")
    private String totalAmount;
}