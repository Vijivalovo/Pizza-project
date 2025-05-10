package com.example.users.DTO.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokensResponse {
    private String accessToken;
    private String refreshToken;
}
