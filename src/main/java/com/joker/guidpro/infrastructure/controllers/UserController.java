package com.joker.guidpro.infrastructure.controllers;

import com.joker.guidpro.application.commandService.impl.UserService;
import com.joker.guidpro.domains.models.agregates.User;
import com.joker.guidpro.infrastructure.controllers.dto.ResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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
}
