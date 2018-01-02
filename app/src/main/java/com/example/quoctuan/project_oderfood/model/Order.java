package com.example.quoctuan.project_oderfood.model;

/**
 * Created by Admin on 12/30/2017.
 */

public class Order {

    private String ProductID;
    private String ProductName;
    private String Quality;
    private String Price;
    private String Discount;

    public Order() {
    }

    public Order(String productID, String productName, String quality, String price, String discount) {
        ProductID = productID;
        ProductName = productName;
        Quality = quality;
        Price = price;
        Discount = discount;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getQuality() {
        return Quality;
    }

    public void setQuality(String quality) {
        Quality = quality;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }
}
