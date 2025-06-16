package com.accenture.order.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Item {

    @NotNull(message = "Item ID cannot be null")
    @Size(min = 3, max = 20, message = "Item ID must be between 3 and 20 characters")
    private String itemId;

    @NotNull(message = "Item name cannot be null")
    @Size(min = 2, max = 50, message = "Item name must be between 2 and 50 characters")
    private String itemName;

    @Positive(message = "Quantity must be greater than 0")
    private int quantity;

    @Positive(message = "Price must be greater than 0")
    private double price;
}
