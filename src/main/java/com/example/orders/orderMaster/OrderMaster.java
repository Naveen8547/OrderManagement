package com.example.orders.orderMaster;


import com.example.orders.auditable.Auditable;
import com.example.orders.user.User;
import com.example.orders.user.address.Address;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Master_Order")
public class OrderMaster extends Auditable<String> {

    @Id
    @Column(name = "master_order_id")
    @GeneratedValue
    private Long masterOrderId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Delivery_Address")
    private Address address;

    @Column(name = "date")
    private Date date;

    @Column(name = "sub_total")
    private Float subTotal;

    @Column(name = "total_discount")
    private Float discount;

    @Column(name = "status")
    private String status;

    public OrderMaster() {
    }

    public OrderMaster(User user, Address address, Date date, Float subTotal, Float discount, String status) {
        this.user = user;
        this.address = address;
        this.date = date;
        this.subTotal = subTotal;
        this.discount = discount;
        this.status = status;
    }

    public Long getMasterOrderId() {
        return masterOrderId;
    }

    public void setMasterOrderId(Long masterOrderId) {
        this.masterOrderId = masterOrderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Float subTotal) {
        this.subTotal = subTotal;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}


