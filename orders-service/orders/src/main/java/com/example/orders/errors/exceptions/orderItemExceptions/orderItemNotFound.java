package com.example.orders.errors.exceptions.orderItemExceptions;

public class orderItemNotFound extends RuntimeException {
    public orderItemNotFound(String message) {
        super(message);
    }
}
