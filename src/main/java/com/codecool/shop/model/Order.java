package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private static Order instance = null;

    private List<OrderList> items = new ArrayList<>();
    private int totalSum;
    private final String CURRENCY = "Credits";


    public Order(String orderName, String orderDescription) {
        super(orderName, orderDescription);
    }

    public Order(int id, String name, String description, int totalSum, String currency, List<OrderList> orderList) {
        super(name, description);
        this.id = id;
        this.totalSum = totalSum;
        this.contactInfo = contactInfo;
    }

    public static Order getInstance() {
        if (instance == null) {
            String orderName = "Current order";
            String description = "The only active order for now";
            instance = new Order(orderName, description);
        }
        return instance;
    }

    public void addProduct(Product product) {
        OrderList orderList = getOrderListByProductId(product.getId());
        if (orderList == null) {
            String orderListName = "Order list " + product.getName();
            String orderListDescription = "Some " + product.getName() + "s";
            OrderList newOrderList = new OrderList(product, orderListName, orderListDescription);
            items.add(newOrderList);
        } else {
            orderList.add(product);
        }
        totalSum += product.getDefaultPrice();
    }

    public void removeProduct(Product product) {
        OrderList orderList = getOrderListByProductId(product.getId());
        orderList.remove(product);
        totalSum -= product.getDefaultPrice();
        if (orderList.isEmpty()) {
            items.remove(orderList);
        }
    }

}
