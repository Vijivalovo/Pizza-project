package com.example.orders.errors.exceptions.orderExceptions;

public class orderNotFound extends RuntimeException {
    public orderNotFound(String message) {
        super(message);
    }
}