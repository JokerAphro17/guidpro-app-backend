package com.joker.guidpro.infrastructure.controllers;

import com.joker.guidpro.application.commandService.impl.AuthService;
import com.joker.guidpro.application.outboundService.impl.KeycloakUserServiceImpl;
import com.joker.guidpro.domains.models.commandes.LoginCmd;
import com.joker.guidpro.infrastructure.controllers.dto.LoginDto;
import com.joker.guidpro.infrastructure.controllers.dto.ResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController( AuthService authService) {
        this.authService = authService;

    }

    @PostMapping("/signin")
    public ResponseEntity<ResponseDTO> login(@Valid @RequestBody LoginCmd loginCmd) {
        LoginDto loginDto = authService.login(loginCmd.getUsername(), loginCmd.getPassword());
        return ResponseEntity.ok(new ResponseDTO("Login successful", loginDto, true));
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseDTO> logout(Principal principal) {
        authService.logout(principal);
        return ResponseEntity.ok(new ResponseDTO("Logout successful", null, true));
    }
}
