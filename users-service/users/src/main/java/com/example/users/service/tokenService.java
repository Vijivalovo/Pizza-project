package com.example.users.service;

import com.example.users.DTO.token.Payload;
import com.example.users.DTO.token.TokensResponse;
import com.example.users.models.*;
import com.example.users.repository.tokenRepository;
import com.example.users.repository.userRepository;
import com.example.users.service.Interfaces.tokenInterface;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.io.Decoders;

@Service
public class tokenService implements tokenInterface
{
    @Autowired
    private tokenRepository TokenRepository;

    @Autowired userRepository UserRepository;

    @Value("${jwt.access.secret}")
    private String jwtAccessSecret;

    @Value("${jwt.refresh.secret}")
    private String jwtRefreshSecret;

    @Value("${accessTokenExpiration}")
    private long accessTokenExpiration;

    @Value("${refreshTokenExpiration}")
    private long refreshTokenExpiration;

    public TokensResponse generateTokens(Payload payload)
    {
        return new TokensResponse("Bearer " + generateToken(payload, jwtAccessSecret, accessTokenExpiration), "Bearer " + generateToken(payload, jwtRefreshSecret, refreshTokenExpiration));
    }

    private SecretKey getSecretKey()
    {
        byte[] keyBytes = Decoders.BASE64.decode(jwtRefreshSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static SecretKey getSigningKey(String secret)
    {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Payload validateRefreshToken(String token)
    {
        SecretKey secretKey = getSigningKey(jwtRefreshSecret);

        JwtParser parser = Jwts.parser()
            .verifyWith(secretKey)
            .build();
            
        Claims claims = parser.parseSignedClaims(token).getPayload();

        Payload payload = new Payload();
        payload.setId(claims.get("id", Integer.class));
        payload.setRole(claims.get("role", Boolean.class));

        return payload;
    }

    public Payload validateAccessToken(String token)
    {
        SecretKey secretKey = getSigningKey(jwtAccessSecret);

        JwtParser parser = Jwts.parser()
            .verifyWith(secretKey)
            .build();
            
        Claims claims = parser.parseSignedClaims(token).getPayload();

        Payload payload = new Payload();
        payload.setId(claims.get("id", Integer.class));
        payload.setRole(claims.get("role", Boolean.class));

        return payload;
    }

    @Async
    public String generateToken(Payload payload, String secret, long expiration)
    {
        SecretKey key = getSigningKey(secret);

        return Jwts.builder()
                .claim("id", payload.getId())
                .claim("role", payload.getRole())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }

    @Async
    public tokens saveToken(int id, String refreshToken)
    {
        tokens sortToken = TokenRepository.findTokenByUserId(id);

        if(sortToken != null)
        {
            sortToken.setTokens(refreshToken);

            return TokenRepository.save(sortToken);
        }

        users NewUser = UserRepository.findById(id).orElse(null);
        
        tokens NewToken = new tokens();
        NewToken.setTokens(refreshToken);
        NewToken.setUser(NewUser);

        TokenRepository.save(NewToken);

        return NewToken;
    }

    @Async
    public void removeToken(int id)
    {
        TokenRepository.deleteById(TokenRepository.findTokenByUserId(id).getId());
    }

    @Async
    public String findTokenByUserId(int id)
    {
        return TokenRepository.findTokenByUserId(id).getTokens();
    }
}
