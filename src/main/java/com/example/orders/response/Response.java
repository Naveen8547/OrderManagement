package com.example.orders.response;

public class Response {

    private String status;

    private float value;

    public Response() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Response(String status, float value) {
        this.status = status;
        this.value = value;
    }
}
