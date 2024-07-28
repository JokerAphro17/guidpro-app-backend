package com.joker.guidpro.application.commandService.interfaces;

import com.joker.guidpro.domains.models.agregates.User;
import com.joker.guidpro.domains.models.commandes.users.UpdateUserCmd;
import com.joker.guidpro.domains.models.commandes.users.UserCmd;
import com.joker.guidpro.domains.models.enums.UserSatus;

import java.util.Set;
import java.util.UUID;

public interface UserServiceInter {

    Set<User> getAllUsers(String type);

    User getUserById(UUID id);

    User createUser(UserCmd userCmd);

    User updateUser(UUID id, UpdateUserCmd userCmd);


    void updateUserStatus(UUID id, UserSatus status);

    void resetUserPassword(UUID id);
}
