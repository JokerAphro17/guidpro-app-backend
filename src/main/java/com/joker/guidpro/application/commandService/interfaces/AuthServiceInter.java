package com.joker.guidpro.application.commandService.interfaces;

import com.joker.guidpro.infrastructure.controllers.dto.LoginDto;

public interface AuthServiceInter {

    LoginDto login(String username, String password);
}
