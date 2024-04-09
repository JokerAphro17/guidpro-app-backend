package com.joker.guidpro.application.outboundService.interfaces;


import com.joker.guidpro.domains.models.agregates.User;
import org.keycloak.representations.idm.UserRepresentation;

import java.security.Principal;

public interface KeycloakUserService {



    void deleteUserById(String userId);

    void logout(Principal principal);

    void updateUser(User user);

    void resetUserPassword(String userId, String password);

    void updateUserStatus(String userId, boolean status);

    void assignRole(String userId, String roleName);
}


