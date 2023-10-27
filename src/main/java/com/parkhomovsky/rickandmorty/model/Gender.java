package com.parkhomovsky.rickandmorty.model;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    UNKNOWN("Unknown"),
    GENDERLESS("Genderless");
    private String value;

    Gender(String value) {
        this.value = value;
    }
}
