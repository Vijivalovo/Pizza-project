package com.example.users.errors.exceptions.userExceptions;

public class WrongPassword extends RuntimeException
{
    public WrongPassword(String message)
    {
        super(message);
    }
}
