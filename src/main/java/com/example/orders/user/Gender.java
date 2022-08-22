package com.example.orders.user;

public enum Gender {
    male("male"),
    female("female"),
    others("others");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }
}
