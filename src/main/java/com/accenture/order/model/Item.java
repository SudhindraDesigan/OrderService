package com.accenture.order.model;

import lombok.Data;

@Data
public class Item {

    private String itemId;
    private String itemName;
    private int quantity;
    private double price;
}
