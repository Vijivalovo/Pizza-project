package com.example.users.errors.exceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.users.errors.BusinessError;
import com.example.users.errors.exceptions.userExceptions.Unauthorized;
import com.example.users.errors.exceptions.userExceptions.UserAlreadyExist;
import com.example.users.errors.exceptions.userExceptions.UserNotFound;
import com.example.users.errors.exceptions.userExceptions.WrongPassword;

public class GlobalExceptionHadler
{
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BusinessError> Exception(Exception e)
    {
        BusinessError err = new BusinessError(e.getMessage());

        return ResponseEntity.status(500).body(err);
    }

    @ExceptionHandler({
        UserNotFound.class,
        Unauthorized.class,
        UserAlreadyExist.class,
        WrongPassword.class
    })
    public ResponseEntity<BusinessError> handleNotFound(RuntimeException e) {
        BusinessError err = new BusinessError(e.getMessage());
        return ResponseEntity.status(404).body(err);
    }
}
