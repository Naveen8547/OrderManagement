package com.example.orders.wallet;


public class WalletCreateRequest {
    private Long walletBalance;

    private long userId;

    public Long getWalletBalance() {


        return walletBalance;
    }

    public void setWalletBalance(Long walletBalance) {
        this.walletBalance = walletBalance;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
