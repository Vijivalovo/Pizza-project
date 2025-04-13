package com.example.orders.errors.exceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.orders.errors.exceptions.orderExceptions.OrderNotFound;
import com.example.orders.errors.exceptions.orderItemExceptions.OrderItemNotFound;
import com.example.orders.errors.BusinessError;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BusinessError> Exception(Exception e)
    {
        BusinessError err = new BusinessError(e.getMessage());

        return ResponseEntity.status(500).body(err);
    }

    @ExceptionHandler(OrderNotFound.class)
    public ResponseEntity<BusinessError> OrderNotFound(OrderNotFound e)
    {
        BusinessError err = new BusinessError(e.getMessage());

        return ResponseEntity.status(404).body(err);
    }

    @ExceptionHandler(OrderItemNotFound.class)
    public ResponseEntity<BusinessError> OrderItemNotFound(OrderItemNotFound e)
    {
        BusinessError err = new BusinessError(e.getMessage());

        return ResponseEntity.status(404).body(err);
    }
}
