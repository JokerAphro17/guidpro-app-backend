package com.joker.guidpro.application.commandService.interfaces;

import com.joker.guidpro.infrastructure.controllers.dto.LoginDto;

import java.security.Principal;

public interface AuthServiceInter {

    LoginDto login(String username, String password);

    void logout(Principal principal);
}
