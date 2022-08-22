package com.example.orders.order;

import java.util.ArrayList;

public class ItemOrderCreateRequest {

    private ArrayList<OrderCreateRequest> orderCreateRequests;

    private String token;

    private Long addressId;

    public ItemOrderCreateRequest(ArrayList<OrderCreateRequest> orderCreateRequests, String token, Long addressId) {
        this.orderCreateRequests = orderCreateRequests;
        this.token = token;
        this.addressId = addressId;
    }

    public ArrayList<OrderCreateRequest> getOrderCreateRequests() {
        return orderCreateRequests;
    }

    public void setOrderCreateRequests(ArrayList<OrderCreateRequest> orderCreateRequests) {
        this.orderCreateRequests = orderCreateRequests;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
