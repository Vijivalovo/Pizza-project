package com.example.users.controller;

public class ResponseClass<T>
{
    private String message;
    private T user;

    public ResponseClass(String message, T user)
    {
        this.message = message;
        this.user = user;
    }

    public String getMessage()
    {
        return message;
    }

    public Object getOrder()
    {
        return user;
    }
}
