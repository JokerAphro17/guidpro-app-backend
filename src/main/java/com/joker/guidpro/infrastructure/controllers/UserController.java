package com.joker.guidpro.infrastructure.controllers;

import com.joker.guidpro.application.commandService.impl.UserService;
import com.joker.guidpro.config.Utils;
import com.joker.guidpro.domains.models.agregates.User;
import com.joker.guidpro.domains.models.commandes.auth.UserCmd;
import com.joker.guidpro.infrastructure.controllers.dto.ResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllUsers(@RequestParam String type) {
        Set<User> users = userService.getAllUsers(type);
        return ResponseEntity.ok(new ResponseDTO("Users fetched successfully", users, true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable String id) {
        // verify if the id is a valid UUID
        if(!Utils.isValidUUID(id)){
            return ResponseEntity.badRequest().body(new ResponseDTO("User not found", null, false));
        }

        User user = userService.getUserById(UUID.fromString(id));
        if(user == null){
            return ResponseEntity.badRequest().body(new ResponseDTO("User not found", null, false));
        }
        return ResponseEntity.ok(new ResponseDTO("User fetched successfully", user, true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable String id, @RequestBody UserCmd userCmd) {
        User updatedUser = userService.updateUser(UUID.fromString(id), userCmd);
        return ResponseEntity.ok(new ResponseDTO("User updated successfully", updatedUser, true));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createUser(@RequestBody UserCmd userCmd) {
        User user = userService.createUser(userCmd);
        return ResponseEntity.ok(new ResponseDTO("User created successfully", user, true));
    }
}
