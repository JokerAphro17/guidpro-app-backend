package com.joker.guidpro.application.commandService.interfaces;

import com.joker.guidpro.domains.models.agregates.User;
import com.joker.guidpro.domains.models.commandes.auth.UserCmd;

import java.util.Set;
import java.util.UUID;

public interface UserServiceInter {

    Set<User> getAllUsers(String type);

    User getUserById(UUID id);

    User createUser(UserCmd userCmd);

    User updateUser(UUID id, UserCmd userCmd);
}
