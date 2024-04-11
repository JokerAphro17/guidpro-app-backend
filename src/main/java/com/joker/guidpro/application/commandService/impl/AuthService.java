package com.joker.guidpro.application.commandService.impl;

import com.joker.guidpro.application.commandService.interfaces.AuthServiceInter;
import com.joker.guidpro.application.exceptions.LoginFailedException;
import com.joker.guidpro.application.exceptions.UnauthorizedException;
import com.joker.guidpro.application.outboundService.impl.KeycloakUserServiceImpl;
import com.joker.guidpro.domains.models.agregates.Expert;
import com.joker.guidpro.domains.models.agregates.Novice;
import com.joker.guidpro.domains.models.agregates.User;
import com.joker.guidpro.domains.models.commandes.auth.RegisterCmd;
import com.joker.guidpro.domains.models.commandes.users.ChangePasswordCmd;
import com.joker.guidpro.domains.models.commandes.users.UserCmd;
import com.joker.guidpro.domains.models.enums.UserRoles;
import com.joker.guidpro.domains.models.enums.UserSatus;
import com.joker.guidpro.infrastructure.controllers.dto.LoginDto;
import com.joker.guidpro.infrastructure.controllers.dto.TokenDto;
import com.joker.guidpro.infrastructure.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
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

        switch (UserRoles.valueOf(role)) {
            case EXPERT:
                return createUserAndAssignRole(registerCmd, Expert.class, "EXPERT");
            case NOVICE:
                return createUserAndAssignRole(registerCmd, Novice.class, "NOVICE");
            default:
                return null;
        }
    }

    private <T extends User> T createUserAndAssignRole(RegisterCmd registerCmd, Class<T> userClass, String role) {
        T user = modelMapper.map(registerCmd, userClass);
        user.setStatus(UserSatus.ACTIVE);
        String keycloakId = keycloakUserService.createUser(user, registerCmd.getPassword());
        keycloakUserService.assignRole(keycloakId, role);
        user.setPassword(bCryptPasswordEncoder.encode(registerCmd.getPassword()));
        user.setKeycloakId(keycloakId);
        return userRepo.save(user);
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
