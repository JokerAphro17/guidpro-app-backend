package com.joker.guidpro.application.outboundService.impl;



import com.joker.guidpro.application.exceptions.LoginFailedException;
import com.joker.guidpro.application.outboundService.interfaces.KeycloakUserService;
import com.joker.guidpro.domains.models.agregates.User;
import com.joker.guidpro.domains.models.commandes.users.ChangePasswordCmd;
import com.joker.guidpro.infrastructure.controllers.dto.TokenDto;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

@Service
@Slf4j

public class KeycloakUserServiceImpl implements KeycloakUserService {

    @Value("${keycloak.realm}")
    private String realm;
    private final Keycloak keycloak ;


    @Value("${keycloak.adminClientId}")
    private String adminClientId;

    @Value("${keycloak.adminClientSecret}")
    private String adminClientSecret;

    @Value("${keycloak.client")
    private String client;

    @Value("${keycloak.urls.auth}")
    private String authUrl;


    private final ModelMapper modelMapper;

    public KeycloakUserServiceImpl(Keycloak keycloak, ModelMapper modelMapper) {
        this.keycloak = keycloak;
        this.modelMapper = modelMapper;
    }










    public TokenDto login(String username, String password) {

        Map<String, Object> Configuratial = new HashMap<>();
        Configuratial.put("secret", adminClientSecret);
        Configuratial.put("provider", "secret");

        Configuration configuration = new Configuration(authUrl, realm, adminClientId, Configuratial, null);
        AuthzClient authzClient = AuthzClient.create(configuration);

        try {
            AccessTokenResponse accessTokenResponse = authzClient.obtainAccessToken(username, password);
            TokenDto tokenDto = new TokenDto();
            tokenDto.setToken(accessTokenResponse.getToken());
            tokenDto.setRefreshToken(accessTokenResponse.getRefreshToken());
            tokenDto.setExpiresIn(accessTokenResponse.getExpiresIn());
            tokenDto.setTokenType(accessTokenResponse.getTokenType());
            return tokenDto;
        } catch (Exception e) {
            throw new LoginFailedException("Email ou mot de passe incorrect");
        }

    }



    @Override
    public void logout(Principal principal) {

        String userId = principal.getName();
        UserResource userResource = getUserResource(userId);
        userResource.logout();
    }


    public String createUser(User user, String password) {
        UserRepresentation userRepresentation = modelMapper.map(user, UserRepresentation.class);
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);
        userRepresentation.setUsername(user.getEmail());
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(password);
        credentialRepresentation.setTemporary(false);
        userRepresentation.setCredentials(Arrays.asList(credentialRepresentation));
        UsersResource usersResource = getUsersResource();
        // virify if the user already exist
        List<UserRepresentation> userRepresentations = usersResource.search(user.getEmail());
        if (userRepresentations.isEmpty()) {
            Response response = usersResource.create(userRepresentation);
            if (response.getStatus() == 201) {
                List<UserRepresentation> userRepresentations1 = usersResource.search(user.getEmail());

        return  userRepresentations1.get(0).getId();
            }
        } else {
            return userRepresentations.get(0).getId();
        }
        return null;

    }

    @Override
    public void updateUser(User user) {
        UserResource userResource = getUserResource(user.getKeycloakId());
        UserRepresentation userRepresentation = modelMapper.map(user, UserRepresentation.class);
        userResource.update(userRepresentation);
    }



    @Override
    public void resetUserPassword(String userId, String password){
        UserResource userResource = getUserResource(userId);
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(password);
        credentialRepresentation.setTemporary(false);
        userResource.resetPassword(credentialRepresentation);

    }
    @Override
    public void deleteUserById(String userId) {

        getUsersResource().delete(userId);
    }

    @Override
    public void updateUserStatus(String userId, boolean status) {
        UserResource userResource = getUserResource(userId);
        UserRepresentation userRepresentation = userResource.toRepresentation();
        userRepresentation.setEnabled(status);
        userResource.update(userRepresentation);
    }





    public UserResource getUserResource(String userId){
        UsersResource usersResource = getUsersResource();
        return usersResource.get(userId);
    }
    private UsersResource getUsersResource() {
        RealmResource realm1 = keycloak.realm(realm);
        return realm1.users();
    }


    public UserRepresentation getUserById(String userId) {
        return  getUsersResource().get(userId).toRepresentation();
    }

    @Override
    public void assignRole(String userId, String roleName) {

        UserResource userResource = getUserResource(userId);
        RolesResource rolesResource = getRolesResource();

        RoleRepresentation representation = rolesResource.get(roleName).toRepresentation();
        userResource.roles().realmLevel().add(Collections.singletonList(representation));
    }

    private RolesResource getRolesResource(){
        return  keycloak.realm(realm).roles();
    }
}

