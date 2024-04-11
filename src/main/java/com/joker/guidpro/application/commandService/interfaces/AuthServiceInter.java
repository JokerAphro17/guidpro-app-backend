package com.joker.guidpro.application.commandService.interfaces;

import com.joker.guidpro.domains.models.agregates.User;
import com.joker.guidpro.domains.models.commandes.auth.RegisterCmd;
import com.joker.guidpro.domains.models.commandes.users.ChangePasswordCmd;
import com.joker.guidpro.domains.models.commandes.users.UserCmd;
import com.joker.guidpro.infrastructure.controllers.dto.LoginDto;

import java.security.Principal;

public interface AuthServiceInter {

    LoginDto login(String username, String password);

    void logout(Principal principal);

    User register(RegisterCmd registerCmd);


    User updateProfile(Principal principal, UserCmd userCmd);

}
