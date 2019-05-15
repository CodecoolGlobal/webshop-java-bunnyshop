package com.codecool.shop.customer;

public class CustomerContactInfo {
    private int id;

    private String name;

    private String email;
    private String phoneNumber;
    private CustomerAddress billingAddress;
    private CustomerAddress shippingAddress;
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBillingAddressString() {
        return this.billingAddress.toString();
    }


    public String getShippingAddressString() {
        return this.shippingAddress.toString();
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomerContactInfo(String name, String email, String phoneNumber, CustomerAddress billingAddress, CustomerAddress shippingAddress) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }

    public CustomerContactInfo(int id, String name, String email, String phoneNumber, CustomerAddress billingAddress, CustomerAddress shippingAddress) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("id: " + this.id + "," )
                .append("name: " + this.name + ",")
                .append("email: " + this.email + ",")
                .append("phone: " + this.phoneNumber + ",")
                .append("billing address: " + this.billingAddress.getAddress()+ ",")
                .append("billing city: " + this.billingAddress.getCity()+ ",")
                .append("billing country: " + this.billingAddress.getCountry()+ ",")
                .append("billing country: " + this.billingAddress.getZipCode()+ ",")
                .append("shipping address: " + this.shippingAddress.getAddress()+ ",")
                .append("shipping city: " + this.shippingAddress.getCity()+ ",")
                .append("shipping country: " + this.shippingAddress.getCountry()+ ",")
                .append("shipping country: " + this.shippingAddress.getZipCode()+ ",");

        return builder.toString();
    }
}
