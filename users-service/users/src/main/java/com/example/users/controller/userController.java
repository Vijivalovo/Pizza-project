package com.example.users.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.users.DTO.token.Payload;
import com.example.users.DTO.user.loginDTO;
import com.example.users.DTO.user.registrationDTO;
import com.example.users.models.users;
import com.example.users.service.tokenService;
import com.example.users.service.userService;


@RestController
@RequestMapping("/api/users")
public class userController
{
       @Autowired
       private userService UserService;

       @Autowired
       private tokenService TokenService;

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

       @GetMapping("/validateForData")
       public ResponseEntity<ResponseClass<Payload>> validateForData(@RequestHeader(value = "authorization", defaultValue = "") String accessToken)
       {
          String[] parts = accessToken.split(" ");
          String token = parts[1];

          ResponseClass<Payload> response = new ResponseClass<>("1", TokenService.validateAccessToken(token));
          return ResponseEntity.status(200).body(response);
       }

       @GetMapping("/findById")
       public ResponseEntity<Map<String, Object>> findById(@RequestHeader(value = "authorization", defaultValue = "") String accessToken)
       {

          System.out.println(accessToken + " -->accessToken");
          String[] parts = accessToken.split(" ");
          String token = parts[1];

          Payload response = TokenService.validateAccessToken(token);
          users user = UserService.findById(response.getId());
          System.out.println(user + " -->@GetMapping(\"/findById\")");

          Map<String, Object> responseBody = new HashMap<>();
          responseBody.put("body", user);
          return ResponseEntity.status(200).body(responseBody);
       }
       
}
