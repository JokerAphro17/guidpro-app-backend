package com.joker.guidpro.infrastructure.controllers.dto;

import com.joker.guidpro.domains.models.agregates.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class LoginDto {
    private User user;
    private TokenDto tokens;
}
