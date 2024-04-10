package com.joker.guidpro.application.commandService.impl;

import com.joker.guidpro.application.commandService.interfaces.AuthServiceInter;
import com.joker.guidpro.application.exceptions.LoginFailedException;
import com.joker.guidpro.application.exceptions.UnauthorizedException;
import com.joker.guidpro.application.outboundService.impl.KeycloakUserServiceImpl;
import com.joker.guidpro.domains.models.agregates.Expert;
import com.joker.guidpro.domains.models.agregates.Novice;
import com.joker.guidpro.domains.models.agregates.User;
import com.joker.guidpro.domains.models.commandes.auth.RegisterCmd;
import com.joker.guidpro.domains.models.commandes.users.UserCmd;
import com.joker.guidpro.domains.models.enums.UserRoles;
import com.joker.guidpro.domains.models.enums.UserSatus;
import com.joker.guidpro.infrastructure.controllers.dto.LoginDto;
import com.joker.guidpro.infrastructure.controllers.dto.TokenDto;
import com.joker.guidpro.infrastructure.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Objects;

@Service
@AllArgsConstructor
@Transactional
public class AuthService implements AuthServiceInter {


    private final KeycloakUserServiceImpl keycloakUserService;
    private final UserRepository userRepo;
    private final ModelMapper modelMapper;
    @Override
    public LoginDto login(String username, String password) {
        User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new LoginFailedException("Password or username is incorrect");
        }
        if(user.getStatus().equals(UserSatus.INACTIVE)){
            throw new UnauthorizedException("Votre compte est inactif, veuillez contacter l'administrateur");
        }
       TokenDto tokenDto = keycloakUserService.login(username, password);
        return new LoginDto(user, tokenDto);
    }

    @Override
    public void logout(Principal principal) {

        keycloakUserService.logout(principal);
    }
    @Override
    public User register(RegisterCmd registerCmd) {
        String role = registerCmd.getRole();
        User user = userRepo.findByEmail(registerCmd.getEmail());
        if (user != null) {
            throw new UnauthorizedException("Un utilisateur avec cet email existe déjà");
        }
        if(Objects.equals(role, UserRoles.EXPERT.toString())){
            Expert expert = modelMapper.map(registerCmd, Expert.class);
            expert.setStatus(UserSatus.ACTIVE);
            String keycloakId = keycloakUserService.createUser(expert, registerCmd.getPassword());
            keycloakUserService.assignRole(keycloakId, "EXPERT");
            expert.setKeycloakId(keycloakId);
            return userRepo.save(expert);
        }
        if(Objects.equals(role, UserRoles.NOVICE.toString())){
            Novice novice = modelMapper.map(registerCmd, Novice.class);
            novice.setStatus(UserSatus.ACTIVE);
            String keycloakId = keycloakUserService.createUser(novice, registerCmd.getPassword());
            keycloakUserService.assignRole(keycloakId, "NOVICE");
            novice.setKeycloakId(keycloakId);
            return userRepo.save(novice);
        }

        return null;
    }

    @Override
    public User updateProfile(Principal principal, UserCmd userCmd) {
        String userId = principal.getName();
        User user = userRepo.findByKeycloakId(userId);
        if(user == null){
            throw new UnauthorizedException("User not found");
        }
        User user1 = userRepo.findByEmail(userCmd.getEmail());
        if (user1 != null && !user1.getId().equals(user.getId())) {
            throw new UnauthorizedException("Un utilisateur avec cet email existe déjà");
        }
        modelMapper.map(userCmd, user);
        keycloakUserService.updateUser(user);
        return userRepo.save(user);
    }

}
