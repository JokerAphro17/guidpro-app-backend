package com.joker.guidpro.application.commandService.impl;

import com.joker.guidpro.application.commandService.interfaces.AuthServiceInter;
import com.joker.guidpro.application.exceptions.LoginFailedException;
import com.joker.guidpro.application.outboundService.impl.KeycloakUserServiceImpl;
import com.joker.guidpro.domains.models.agregates.User;
import com.joker.guidpro.infrastructure.controllers.dto.LoginDto;
import com.joker.guidpro.infrastructure.controllers.dto.TokenDto;
import com.joker.guidpro.infrastructure.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@AllArgsConstructor
public class AuthService implements AuthServiceInter {


    private final KeycloakUserServiceImpl keycloakUserService;
    private final UserRepository userRepo;
    @Override
    public LoginDto login(String username, String password) {
        User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new LoginFailedException("Password or username is incorrect");
        }
       TokenDto tokenDto = keycloakUserService.login(username, password);
        return new LoginDto(user, tokenDto);
    }

    @Override
    public void logout(Principal principal) {
        keycloakUserService.logout(principal);
    }


}
