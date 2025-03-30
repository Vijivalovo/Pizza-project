package com.example.orders.errors.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.orders.errors.exceptions.orderExceptions.orderNotFound;
import com.example.orders.errors.exceptions.orderItemExceptions.orderItemNotFound;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class globalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Произошла ошибка");
        response.put("error", e.getMessage());
        response.put("statusCode", 500);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(orderNotFound.class)
    public ResponseEntity<Map<String, Object>> handleOrderNotFound(orderNotFound e) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", e.getMessage());
        response.put("statusCode", 404);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(orderItemNotFound.class)
    public ResponseEntity<Map<String, Object>> handleOrderNotFound(orderItemNotFound e) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", e.getMessage());
        response.put("statusCode", 404);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
