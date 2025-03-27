package com.example.users.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.users.DTO.token.Payload;
import com.example.users.DTO.token.TokensResponse;
import com.example.users.DTO.user.loginDTO;
import com.example.users.DTO.user.registrationDTO;
import com.example.users.models.tokens;
import com.example.users.models.users;
import com.example.users.repository.userRepository;
import com.example.users.service.Interfaces.userInterface;
import com.example.users.service.*;

@Service
public class userService implements userInterface
{
      @Autowired
      private userRepository UserRepository;

      @Autowired
      private tokenService TokenService;
      
      private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

      @Async
      public static String hashPassword(String password)
      {
        return passwordEncoder.encode(password);
      }

      @Async
      public static boolean checkPassword(String rawPassword, String hashedPassword)
      {
        return passwordEncoder.matches(rawPassword, hashedPassword);
      }

      @Async
      public Map<String, Object> registration(registrationDTO dto)
      {
        users NewUser = findByPhoneNumber(dto.getNumber_phone());

        if(NewUser.getId() != null)
        {
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Пользователь уже существует");
        }

        users user2 = new users();

        user2.setPassword(hashPassword(dto.getPassword()));
        user2.setName(dto.getName());
        user2.setNumber_phone(dto.getNumber_phone());
        user2.setRole(true);
        user2.setScores(1000);

        UserRepository.save(user2);

        System.out.println(user2 + "   -->users NewUser = findByPhoneNumber(user.getNumber_phone());");
        System.out.println(user2.getId());

        Payload payload = new Payload();

        payload.setId(user2.getId());
        payload.setRole(user2.getRole());

        TokensResponse tokens = TokenService.generateTokens(payload);

        System.out.println(tokens + "   -->TokensResponse tokens = TokenService.generateTokens(payload);");

        TokenService.saveToken(user2.getId(), tokens.getRefreshToken());

        Map<String, Object> response = new HashMap<>();
        response.put("tokens", tokens);
        response.put("user", user2);
        return response;
      }

      @Async 
      public users findByPhoneNumber(String number)
      {
        List<users> arrUsers = UserRepository.findAll();
        
        users NewUser = new users();

        for (users user : arrUsers)
        {
            if (user.getNumber_phone() == number)
            {
                NewUser = user;
            }
        }

        return NewUser;
      }

      @Async
      public users findByPassword(String name, String password)
      {
        List<users> arrUsers = UserRepository.findAll();

        for (users user : arrUsers)
        {
            if (user.getName().equals(name))
            {
              System.out.println(user.getName() +"  "+ name + "   -->for (users user : arrUsers)");

              if(passwordEncoder.matches(password, user.getPassword()))
              {
                return user;
              }
            }
        }

        return null;
      }

      @Async
      public Map<String, Object> login(loginDTO dto)
      {
        users NewUser = findByPassword(dto.getName(), dto.getPassword());

        System.out.println(NewUser + "   -->users NewUser = findByPassword(dto.getPassword());");

        if(NewUser == null)
        {
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Пользователь не существует");
        }

        boolean isPassEqual = checkPassword(dto.getPassword(), NewUser.getPassword());

        if(isPassEqual == false)
        {
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong password");
        }

        System.out.println(isPassEqual + "   -->boolean isPassEqual = checkPassword(dto.getPassword(), NewUser.getPassword());");

        Payload payload = new Payload();

        payload.setId(NewUser.getId());
        payload.setRole(NewUser.getRole());

        TokensResponse tokens = TokenService.generateTokens(payload);

        System.out.println(tokens + "   -->TokensResponse tokens = TokenService.generateTokens(payload);");

        TokenService.saveToken(NewUser.getId(), tokens.getRefreshToken());

        Map<String, Object> response = new HashMap<>();
        response.put("tokens", tokens);
        response.put("user", NewUser);
        return response;
      }

      @Async
      public void logout(int id)
      {
         TokenService.removeToken(id);
      }
}
