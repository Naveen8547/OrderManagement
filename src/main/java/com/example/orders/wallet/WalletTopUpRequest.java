package com.example.orders.wallet;

public class WalletTopUpRequest {
    private  Long userId;

    private Float amt;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public float getAmt() {
        return amt;
    }

    public void setAmt(Float amt) {
        this.amt = amt;
    }
}
