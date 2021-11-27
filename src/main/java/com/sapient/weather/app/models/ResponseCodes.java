package com.sapient.weather.app.models;

public enum ResponseCodes {

    SUNNY("Use sunscreen lotion"), RAINY("Carry umbrella"), WINDY("It’s too windy, watch out!"), THUNDERSTORMS("Don’t step out! A Storm is brewing!");

    private final String value;

    public String getValue() {
        return this.value;
    }

    private ResponseCodes(String value) {
        this.value = value;
    }
}
