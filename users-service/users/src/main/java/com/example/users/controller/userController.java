package com.example.users.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.users.DTO.user.loginDTO;
import com.example.users.DTO.user.registrationDTO;
import com.example.users.models.users;
import com.example.users.service.userService;

@RestController
public class userController
{
       @Autowired
       private userService UserService;

       @PostMapping("api/users/registration/")
       public ResponseEntity<Map<String, Object>> registration(@RequestBody registrationDTO dto)
       {
            try
            {
                Map<String, Object> response1 = UserService.registration(dto);
                Map<String, Object> response = new HashMap<>();
                response.put("body", response1);
                response.put("message", "Пользователь создан");
                response.put("statusCode", 200);
    
                return ResponseEntity.ok(response);
            }
            catch(Exception e)
            {
                Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при создании пользователя");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
            }
       }

       @PostMapping("api/users/login/")
       public ResponseEntity<Map<String, Object>> login(@RequestBody loginDTO dto)
       {
            try
            {
                Map<String, Object> response1 = UserService.login(dto);
                Map<String, Object> response = new HashMap<>();
                response.put("body", response1);
                response.put("message", "Пользователь вошёл");
                response.put("statusCode", 200);
    
                return ResponseEntity.ok(response);
            }
            catch(Exception e)
            {
                Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при попытке входа");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
            }
       }
}
