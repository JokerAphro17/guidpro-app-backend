package com.joker.guidpro.infrastructure.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class TokenDto {
    private String token;
    private String refreshToken;
    private String tokenType;
}
