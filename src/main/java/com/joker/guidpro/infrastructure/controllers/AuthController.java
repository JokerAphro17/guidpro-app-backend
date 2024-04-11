package com.joker.guidpro.infrastructure.controllers;

import com.joker.guidpro.application.commandService.impl.AuthService;
import com.joker.guidpro.domains.models.agregates.User;
import com.joker.guidpro.domains.models.commandes.auth.LoginCmd;
import com.joker.guidpro.domains.models.commandes.auth.RegisterCmd;
import com.joker.guidpro.domains.models.commandes.users.ChangePasswordCmd;
import com.joker.guidpro.domains.models.commandes.users.UserCmd;
import com.joker.guidpro.domains.models.validations.OnUpdate;
import com.joker.guidpro.infrastructure.controllers.dto.LoginDto;
import com.joker.guidpro.infrastructure.controllers.dto.ResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> register(@Valid @RequestBody RegisterCmd registerCmd) {
        if(!registerCmd.isPasswordMatch()){
            return ResponseEntity.badRequest().body(new ResponseDTO("Les mots de passe ne correspondent pas", null, false));
        }
       if(registerCmd.isRoleInValid()){
           return ResponseEntity.badRequest().body(new ResponseDTO("Role invalide", null, false));
       }
        User user = authService.register(registerCmd);
        return ResponseEntity.ok(new ResponseDTO("User created successfully", user, true));
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseDTO> logout(Principal principal, HttpServletRequest request) {
            authService.logout(principal);
            request.getSession().invalidate();
        return ResponseEntity.ok(new ResponseDTO("Logout successful", null, true));
    }

    // update profile
    @PutMapping("/profile")
    public ResponseEntity<ResponseDTO> updateProfile(Principal principal, @Validated(OnUpdate.class) @RequestBody UserCmd userCmd) {
        User updatedUser = authService.updateProfile(principal, userCmd);
        return ResponseEntity.ok(new ResponseDTO("Profile updated successfully", updatedUser, true));
    }

    // change password
    @PutMapping("/change-password")
    public ResponseEntity<ResponseDTO> changePassword(Principal principal, @Valid @RequestBody ChangePasswordCmd changePasswordCmd) {
        if(!changePasswordCmd.isPasswordMatch()){
            return ResponseEntity.badRequest().body(new ResponseDTO("Les mots de passe ne correspondent pas", null, false));
        }
        authService.changePassword(principal, changePasswordCmd);
        return ResponseEntity.ok(new ResponseDTO("Password changed successfully", null, true));
    }
}
