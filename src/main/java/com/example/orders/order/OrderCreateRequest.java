package com.example.orders.order;

public class OrderCreateRequest {


    private Long inventoryId;

    private Long qty;

    public OrderCreateRequest(Long inventoryId, Long qty) {
        this.inventoryId = inventoryId;
        this.qty = qty;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }
}
