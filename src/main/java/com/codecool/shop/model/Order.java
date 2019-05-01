package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private static Order instance = null;

    private List<LineItem> items = new ArrayList<>();
    private int totalSum;
    private final String CURRENCY = "Credits";


    public Order(String orderName, String orderDescription) {
        super(orderName, orderDescription);
    }

}
