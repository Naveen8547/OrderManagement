package com.example.orders.order;

import com.example.orders.auditable.Auditable;
import com.example.orders.orderMaster.OrderMaster;

import javax.persistence.*;


@Entity
@Table(name = "itemOrder")
public class Order extends Auditable<String> {

    @Id
    @Column(name = "order_id")
    @GeneratedValue
    private long orderId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_master_orderid")
    private OrderMaster orderMaster;


    @Column(name = "product_id")
    private Long productId;

    @Column(name = "qty")
    private Long qty;

    @Column(name = "product_price")
    private float price;


    @Column(name = "delivery_status")
    private String status;

    @Column(name = "discount")
    private float discount;

    public Order(OrderMaster orderMaster, Long productId, Long qty, float price, String status, float discount) {
        this.orderMaster = orderMaster;
        this.productId = productId;
        this.qty = qty;
        this.price = price;
        this.status = status;
        this.discount = discount;
    }

    public Order() {
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public OrderMaster getOrderMaster() {
        return orderMaster;
    }

    public void setOrderMaster(OrderMaster orderMaster) {
        this.orderMaster = orderMaster;
    }

    public Long getInventory() {
        return productId;
    }

    public void setInventory(Long productId) {
        this.productId = productId;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
