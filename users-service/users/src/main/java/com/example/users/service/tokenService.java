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
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.*;
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

    private final long accessTokenExpiration = 50 * 60 * 1000; // 50 минут
    private final long refreshTokenExpiration = 24 * 60 * 60 * 1000; // 1 день

    public TokensResponse generateTokens(Payload payload)
    {
        String accessToken = "Bearer " + generateToken(payload, jwtAccessSecret, accessTokenExpiration); // 50 минут
        String refreshToken = "Bearer " + generateToken(payload, jwtRefreshSecret, refreshTokenExpiration); // 1 день
        return new TokensResponse(accessToken, refreshToken);
    }

    public Payload validateRefreshToken(String token)
    {
        try 
        {
            byte[] keyBytes = jwtRefreshSecret.getBytes(StandardCharsets.UTF_8);
            SecretKey secretKey = new SecretKeySpec(keyBytes, "HmacSHA256");

            Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload(); 

            Payload payload = new Payload();
            payload.setId(claims.get("id", Integer.class));
            payload.setRole(claims.get("role", Boolean.class));

            return payload;
        }catch (Exception e)
        {
            return null;
        }
    }

    public String validateAccessToken(String token)
    {
        try 
        {
            byte[] keyBytes = jwtAccessSecret.getBytes(StandardCharsets.UTF_8);
            SecretKey secretKey = new SecretKeySpec(keyBytes, "HmacSHA256");

            Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload(); 

            return claims.getSubject();
        }catch (Exception e)
        {
            return null;
        }
    }

    // public Long getUserIdFromToken(String token)
    // {
    //     Claims claims = Jwts.parser()
    //             .setSigningKey(jwtAccessSecret)
    //             .parseClaimsJws(token)
    //             .getBody();
    //     return Long.parseLong(claims.getSubject());
    // }

    public static SecretKey getSigningKey(String secret) {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes); // Генерируем корректный ключ
    }

    @Async
    public String generateToken(Payload payload, String secret, long expiration)
    {
            //byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
            //SecretKey key = new SecretKeySpec(keyBytes, "HmacSHA512");
            SecretKey key = getSigningKey(secret);

            return Jwts.builder()
                    .subject(String.valueOf(payload.getId()))  // Новый метод вместо setSubject()
                    .expiration(new Date(System.currentTimeMillis() + expiration)) // Новый метод вместо setExpiration()
                    .signWith(key) // Передаём корректный SecretKey
                    .compact();
    }

    @Async
    public tokens saveToken(int id, String refreshToken)
    {
        List<tokens> Tokens = TokenRepository.findAll();

        System.out.println(Tokens + "   -->List<tokens> Tokens = TokenRepository.findAll();");

        tokens sortToken = new tokens();

        for (tokens Token : Tokens)
        {
            if (Token.getUser().getId() == id)
            {
                sortToken = Token;
            }
        }

        System.out.println(sortToken + "   -->sortToken");

        if(sortToken.getId() != null)
        {
            //String oldRefreshToken = sortToken.getTokens();
            sortToken.setTokens(refreshToken);

            return TokenRepository.save(sortToken);
        }

        users NewUser = UserRepository.findById(id).orElse(null);

        System.out.println(NewUser + "   -->UserRepository.findById(id).orElse(null);");
        System.out.println(refreshToken + "   -->NewToken.setTokens(refreshToken);");
        
        tokens NewToken = new tokens();
        NewToken.setTokens(refreshToken);
        NewToken.setUser(NewUser);

        TokenRepository.save(NewToken);

        return NewToken;
    }

    @Async
    public void removeToken(int id)
    {
        List<tokens> Tokens = TokenRepository.findAll();

        tokens sortToken = new tokens();

        for (tokens Token : Tokens)
        {
            if (Token.getUser().getId() == id)
            {
                sortToken = Token;
            }
        }

        TokenRepository.deleteById(sortToken.getId());
    }
}
