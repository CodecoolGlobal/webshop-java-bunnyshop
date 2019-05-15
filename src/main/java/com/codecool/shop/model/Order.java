package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Order extends BaseModel{

    private static Order instance = null;

    private List<OrderList> items = new ArrayList<>();
    private int totalSum;
    private final String CURRENCY = "USD";


    public Order(String orderName, String orderDescription) {
        super(orderName, orderDescription);
    }

    public Order(int id, String name, String description, int totalSum, String currency, List<OrderList> orderList) {
        super(name, description);
        this.id = id;
        this.totalSum = totalSum;
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


    public List<OrderList> getItems() {
        return items;
    }


    public void removeProduct(Product product) {
        OrderList orderList = getOrderListByProductId(product.getId());
        orderList.remove(product);
        totalSum -= product.getDefaultPrice();
        if (orderList.isEmpty()) {
            items.remove(orderList);
        }
    }

    public OrderList getOrderListByProductId(int productId) {
        return items.stream()
                .filter(item -> item.getProductId() == productId)
                .findFirst()
                .orElse(null);
    }

    public int getTotalNumberOfOrderedProducts() {
        int numberOfOrderedProducts = 0;
        for (int i = 0; i < items.size(); i++) {
            numberOfOrderedProducts += items.get(i).getNumberOfProducts();
        }
        return numberOfOrderedProducts;
    }

    public float getTotalSum(){
        return totalSum;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("id: " + this.id + "," )
                .append("name: " + this.name + ",")
                .append("description: " + this.description + ",")
                .append("items: " + this.items + ",")
                .append("total sum: " + this.totalSum + ",")
                .append("currency: " + this.CURRENCY);
        return builder.toString();
    }

}
