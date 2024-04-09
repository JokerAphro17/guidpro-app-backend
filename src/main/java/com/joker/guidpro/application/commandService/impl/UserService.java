package com.joker.guidpro.application.commandService.impl;

import com.joker.guidpro.application.commandService.interfaces.UserServiceInter;
import com.joker.guidpro.application.exceptions.AlreadyExistsException;
import com.joker.guidpro.application.outboundService.impl.KeycloakUserServiceImpl;
import com.joker.guidpro.config.Utils;
import com.joker.guidpro.domains.models.agregates.Admin;
import com.joker.guidpro.domains.models.agregates.Expert;
import com.joker.guidpro.domains.models.agregates.Novice;
import com.joker.guidpro.domains.models.agregates.User;
import com.joker.guidpro.domains.models.commandes.users.UserCmd;
import com.joker.guidpro.infrastructure.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Service
@Transactional
public class UserService implements UserServiceInter {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;
    private final KeycloakUserServiceImpl keycloakUserService;
    @Override
    public Set<User> getAllUsers(String type) {
        switch (type) {
            case "EXPERT":
                List<Expert> experts = userRepository.findAllExperts();
                return new HashSet<>(experts);
            case "NOVICE":
                List<Novice> novices = userRepository.findAllNovices();
                return new HashSet<>(novices);
            case "ADMIN":
                List<Admin> admins = userRepository.findAllAdmins();
                return new HashSet<>(admins);
            default:
                List<User> users = userRepository.findAll();
                return new HashSet<>(users);
        }

    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User createUser(UserCmd userCmd) {
        User user = null;
        // check if the user is already registered
        if (userRepository.findByEmail(userCmd.getEmail()) != null) {
            throw new AlreadyExistsException("L'utilisateur existe déjà");
        }
        String password = Utils.generateRandomString(8);
        switch (userCmd.getRole()) {
            case "EXPERT":
                user = modelMapper.map(userCmd, Expert.class);
                break;
            case "NOVICE":
                user = modelMapper.map(userCmd, Novice.class);
                break;
            case "ADMIN":
                user = modelMapper.map(userCmd, Admin.class);
                break;
        }
        if (user != null) {
            String keycloakId = keycloakUserService.createUser(user, password);
            assert user != null;
            user.setKeycloakId(keycloakId);
            keycloakUserService.assignRole(keycloakId, userCmd.getRole());
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public User updateUser(UUID id, UserCmd userCmd) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            modelMapper.map(userCmd, user);
            return userRepository.save(user);
        }
        return null;
    }
}
