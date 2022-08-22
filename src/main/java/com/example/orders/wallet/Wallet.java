package com.example.orders.wallet;
import com.example.orders.auditable.Auditable;
import com.example.orders.user.User;

import javax.persistence.*;

@Entity
@Table(name="wallet")
public class Wallet extends Auditable<String>{

    @Id
    @GeneratedValue
    @Column(name="wallet_id")
    private long walletId;

    public long getWalletId() {
        return walletId;
    }

    public void setWalletId(long walletId) {
        this.walletId = walletId;
    }

    @Column(name="wallet_balance")
    private float walletBalance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_user_id")
    private User user;

    public Wallet() {
    }

    public Wallet( float walletBalance, User user) {

        this.walletBalance = walletBalance;
        this.user = user;
    }


    public float getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(float walletBalance) {
        this.walletBalance = walletBalance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
