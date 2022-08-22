package com.example.orders.order;

public class OrderUser {

    private Long masterOrderId;

    private Long orderId;

    private Long productId;

    private Long qty;

    private String Status;

    public OrderUser(Long masterOrderId, Long orderId, Long productId, Long qty, String status) {
        this.masterOrderId = masterOrderId;
        this.orderId = orderId;
        this.productId = productId;
        this.qty = qty;
        Status = status;
    }

    public OrderUser() {
    }

    public Long getMasterOrderId() {
        return masterOrderId;
    }

    public void setMasterOrderId(Long masterOrderId) {
        this.masterOrderId = masterOrderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProduct() {
        return productId;
    }

    public void setProduct(Long productId) {
        this.productId = productId;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
