package com.example.users.service.Interfaces;

import com.example.users.models.users;

import java.util.List;
import java.util.Map;

import com.example.users.DTO.token.*;
import com.example.users.DTO.user.loginDTO;
import com.example.users.DTO.user.registrationDTO;

public interface userInterface
{
    Map<String, Object> registration(registrationDTO dto);
    Map<String, Object> login(loginDTO dto);
    Map<String, Object> refresh(String token);
    void logout(int userId);
    // Map<String, Object> refresh(users user);
    // void deleteUser(int id);
    // users findById(int id);
    
    // List<users> findAll();
    users findByPhoneNumber(String number);
    users findByPassword(String name, String password);

}
