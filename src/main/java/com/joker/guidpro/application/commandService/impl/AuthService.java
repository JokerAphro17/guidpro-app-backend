package com.joker.guidpro.application.commandService.impl;

import com.joker.guidpro.application.commandService.interfaces.AuthServiceInter;
import com.joker.guidpro.application.outboundService.impl.KeycloakUserServiceImpl;
import com.joker.guidpro.infrastructure.controllers.dto.TokenDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService implements AuthServiceInter {


    private final KeycloakUserServiceImpl keycloakUserService;



    public void login(String username, String password) {
       TokenDto tokenDto = keycloakUserService.login(username, password);

    }


}
