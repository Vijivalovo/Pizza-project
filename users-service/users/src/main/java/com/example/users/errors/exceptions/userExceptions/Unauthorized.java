package com.example.users.errors.exceptions.userExceptions;

public class Unauthorized extends RuntimeException
{
    public Unauthorized(String message)
    {
        super(message);
    }
}
