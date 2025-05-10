package com.example.users.errors;

import java.time.ZonedDateTime;

public class BusinessError
{
    private String message;
    private ZonedDateTime createdAt;

    public BusinessError(String message) {
        this.message = message;
        this.createdAt = ZonedDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
