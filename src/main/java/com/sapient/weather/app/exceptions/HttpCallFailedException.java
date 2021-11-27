package com.sapient.weather.app.exceptions;

public class HttpCallFailedException extends Exception {

    public HttpCallFailedException(String message) {
        super(message);
    }
}
