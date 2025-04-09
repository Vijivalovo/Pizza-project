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
@RequestMapping("/api/users")
public class userController
{
       @Autowired
       private userService UserService;

       @PostMapping("/registration")
       public ResponseEntity<ResponseClass<Map<String, Object>>> registration(@RequestBody registrationDTO dto)
       {
            ResponseClass<Map<String, Object>> response = new ResponseClass<>("1", UserService.registration(dto));
            return ResponseEntity.status(201).body(response);
       }

       @PostMapping("/login")
       public ResponseEntity<ResponseClass<Map<String, Object>>> login(@RequestBody loginDTO dto)
       {
            ResponseClass<Map<String, Object>> response = new ResponseClass<>("1", UserService.login(dto));
            return ResponseEntity.status(200).body(response);
       }

       @PostMapping("/logout/{id}")
       public ResponseEntity<ResponseClass<Void>> logout(@PathVariable int id)
       {
            UserService.logout(id);
            ResponseClass<Void> response = new ResponseClass<>("1", null);
            return ResponseEntity.status(200).body(response);
       }

       @PostMapping("/refresh")
       public ResponseEntity<ResponseClass<Map<String, Object>>> refresh(@RequestHeader(value = "Token", defaultValue = "") String refreshToken)
       {
            ResponseClass<Map<String, Object>> response = new ResponseClass<>("1", UserService.refresh(refreshToken));
            return ResponseEntity.status(200).body(response);
       }
}
