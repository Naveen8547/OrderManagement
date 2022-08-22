package com.example.orders.user;


import com.example.orders.auditable.Auditable;

import javax.persistence.*;

@Entity
@Table(name = "Users", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_ph", "email"})})
public class User extends Auditable<String>{


    @Id
    @Column(name = "user_id")
    @GeneratedValue
    private long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_ph", unique = true)
    private String phoneNumber;

    @Column(name = "email")
    private String email;


    @Column(name = "age")
    private Long age;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private Gender gender;

    @Column(name = "nationality")
    private String nationality;

    public User(String name, String phoneNumber, String email, Long age, Gender gender, String nationality) {

        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.nationality = nationality;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

//    public void setId(long id) {
//        this.id = id;
//    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
