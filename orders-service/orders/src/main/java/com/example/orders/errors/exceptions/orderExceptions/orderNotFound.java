package com.example.orders.errors.exceptions.orderExceptions;

public class OrderNotFound extends RuntimeException {
    public OrderNotFound(String message) {
        super(message);
    }
}