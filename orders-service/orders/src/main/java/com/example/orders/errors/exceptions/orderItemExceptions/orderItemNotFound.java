package com.example.orders.errors.exceptions.orderItemExceptions;

public class OrderItemNotFound extends RuntimeException {
    public OrderItemNotFound(String message) {
        super(message);
    }
}
