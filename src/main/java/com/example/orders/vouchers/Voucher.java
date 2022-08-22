package com.example.orders.vouchers;

import com.example.orders.auditable.Auditable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="voucher")
public class Voucher extends Auditable<String> {


    @Id
    @GeneratedValue
    @Column(name = "voucher_id")
    private Long voucherId;

    @Column(name = "token_id",unique = true)
    private String token;

    @Column(name = "discount_per")
    private Float discountPer;

    @Column
    private Date expiryDate;

    @Column
    private Integer userCount;

    @Column
    private Date createdDate;

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate =expiryDate;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate =  new Date(System.currentTimeMillis());;
    }

    public Voucher(Long voucherId, String token, Float discountPer, Date expiryDate, Integer userCount, Date createdDate) {
        this.voucherId = voucherId;
        this.token = token;
        this.discountPer = discountPer;
        this.expiryDate = expiryDate;
        this.userCount = userCount;
        this.createdDate = createdDate;
    }

    public Voucher() {
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Float getDiscountPer() {
        return discountPer;
    }

    public void setDiscountPer(Float discountPer) {
        this.discountPer = discountPer;
    }
}
