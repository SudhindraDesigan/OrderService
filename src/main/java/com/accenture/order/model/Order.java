package com.accenture.order.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data

public class Order {


    private String orderId;
    private List<Item> items;
    private String customerName;
    private String totalAmount;
    private String orderDate;


}
