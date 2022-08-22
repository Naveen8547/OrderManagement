package com.example.orders.order;

public class OrderTemp {

    private Long productId;
    private Long qty;
    private float price;
    private String status;
    private float discount;

    public OrderTemp(Long productId, Long qty, float price, String status, float discount) {
        this.productId = productId;
        this.qty = qty;
        this.price = price;
        this.status = status;
        this.discount = discount;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }
}
