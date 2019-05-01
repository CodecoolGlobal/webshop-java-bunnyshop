package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.ProductDaoMem;

public class OrderList extends BaseModel {
    private int productId;
    private final float UNIT_PRICE;

    private int numberOfProducts;

    private float totalPrice;

    public OrderList(Product product, String name, String description) {
        super(name, description);
        numberOfProducts = 1;
        this.productId = product.getId();
        this.UNIT_PRICE = product.getDefaultPrice();
        this.totalPrice = product.getDefaultPrice();
    }

    public OrderList(Product product, int id, int numberOfProducts, String name, String description,int productId) {
        super(name, description);
        this.id = id;
        this.numberOfProducts = numberOfProducts;
        this.productId = productId;
        this.UNIT_PRICE = product.getDefaultPrice();
        this.totalPrice = product.getDefaultPrice();
    }}