package com.joker.guidpro.domains.models.commandes.auth;

import com.joker.guidpro.domains.models.enums.UserRoles;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterCmd {
    @NotEmpty(message = "Le nom est obligatoire")
    private String lastName;
    @NotEmpty(message = "Le prénom est obligatoire")
    private String firstName;

    @Email(message = "L'email est invalide")
    @NotEmpty(message = "L'email est obligatoire")
    private String email;
    @NotEmpty(message = "Le mot de passe est obligatoire")
    private String password;

    @NotEmpty(message = "La confirmation du mot de passe est obligatoire")
    private String confirmPassword;

    private String phone;
    @NotEmpty(message = "Le role est obligatoire")
    private String role;

    public boolean isPasswordMatch() {
        return password.equals(confirmPassword);
    }

    public boolean isRoleInValid() {
        return !(role.equals(UserRoles.EXPERT.toString()) || role.equals(UserRoles.NOVICE.toString()));
    }

}
