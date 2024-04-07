package com.joker.guidpro.infrastructure.controllers;

import com.joker.guidpro.application.outboundService.impl.KeycloakUserServiceImpl;
import com.joker.guidpro.domains.models.commandes.LoginCmd;
import com.joker.guidpro.infrastructure.controllers.dto.ResponseDTO;
import com.joker.guidpro.infrastructure.controllers.dto.TokenDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

    private final KeycloakUserServiceImpl keycloakUserService;

    public AuthController(KeycloakUserServiceImpl keycloakUserService) {
        this.keycloakUserService = keycloakUserService;
    }

    @PostMapping("/signin")
    public ResponseEntity<ResponseDTO> login(@Valid @RequestBody LoginCmd loginCmd) {
        TokenDto tokenDto = keycloakUserService.login(loginCmd.getUsername(), loginCmd.getPassword());
        return ResponseEntity.ok(new ResponseDTO("login success", tokenDto, true));

    }
}
