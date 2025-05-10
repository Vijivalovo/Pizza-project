package com.example.users.errors.exceptions.userExceptions;

public class UserAlreadyExist extends RuntimeException
{
    public UserAlreadyExist(String message)
    {
        super(message);
    }
}
