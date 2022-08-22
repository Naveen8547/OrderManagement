package com.example.orders.user.address;

import com.example.orders.auditable.Auditable;
import com.example.orders.user.User;

import javax.persistence.*;

@Entity
@Table(name = "Delivery_address")
public class Address extends Auditable<String> {

    @Id
    @Column(name = "address_id")
    @GeneratedValue
    private Long addressId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user")
    private User user;

    @Column(name = "House_no")
    private String houseNo;

    @Column(name = "street")
    private String street;

    @Column(name = "pincode")
    private Long pincode;

    @Column(name = "city")
    private String city;

    public Address(User user, String houseNo, String street, Long pincode, String city) {
        this.user = user;
        this.houseNo = houseNo;
        this.street = street;
        this.pincode = pincode;
        this.city = city;
    }

    public Address() {
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getPincode() {
        return pincode;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
