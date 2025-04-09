package com.example.users.errors.exceptions.userExceptions;

public class UserNotFound extends RuntimeException
{
    public UserNotFound(String message)
    {
        super(message);
    }
}
