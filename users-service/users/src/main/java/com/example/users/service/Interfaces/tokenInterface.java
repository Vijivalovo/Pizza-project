package com.example.users.service.Interfaces;

import com.example.users.DTO.token.Payload;
import com.example.users.DTO.token.TokensResponse;
import com.example.users.models.*;

import io.jsonwebtoken.Claims;

public interface tokenInterface
{
    TokensResponse generateTokens(Payload payload);
    Payload validateRefreshToken(String token);
    Payload validateAccessToken(String token);
    String generateToken(Payload payload, String secret, long expiration);
    tokens saveToken(int id, String token);
    void removeToken(int id);
}
