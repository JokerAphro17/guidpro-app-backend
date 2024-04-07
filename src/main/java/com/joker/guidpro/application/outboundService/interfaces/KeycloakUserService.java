package com.joker.guidpro.application.outboundService.interfaces;


import org.keycloak.representations.idm.UserRepresentation;

import java.security.Principal;

public interface KeycloakUserService {



    void deleteUserById(String userId);

    void logout(Principal principal);

    void resetUserPassword(String userId, String password);

    void assignRole(String userId, String roleName);
}


