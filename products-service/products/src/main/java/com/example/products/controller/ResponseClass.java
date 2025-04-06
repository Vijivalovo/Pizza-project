package com.example.products.controller;

public class ResponseClass<T>
{
    private String message;
    private T order;

    public ResponseClass(String message, T order)
    {
        this.message = message;
        this.order = order;
    }

    public String getMessage()
    {
        return message;
    }

    public Object getOrder()
    {
        return order;
    }
}
