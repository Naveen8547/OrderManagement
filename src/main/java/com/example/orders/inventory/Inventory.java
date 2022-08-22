package com.example.orders.inventory;

import com.example.orders.auditable.Auditable;

import javax.persistence.*;

@Entity
@Table(name = "inventory")

public class Inventory extends Auditable<String> {

    @Id
    @Column(name = "product_id")
    @GeneratedValue
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "stock")
    private Long stock;

    @Column(name = "price")
    private float price;

    @Column(name = "description")
    private String description;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "retailer")
    private String retailer;
    @Column(name = "item_discount")
    private float discount;


    public Inventory() {
    }

    public Inventory(Long productId, String productName, Long stock, float price, String description, String brand, String model, String retailer, float discount) {
        this.productId = productId;
        this.productName = productName;
        this.stock = stock;
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.model = model;
        this.retailer = retailer;
        this.discount = discount;

    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }


    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
