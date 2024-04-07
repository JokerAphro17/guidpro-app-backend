package com.joker.guidpro.domains.models.commandes;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginCmd {
    @NotNull(message = "l'email est obligatoire")
    private String username;
    @NotNull(message = "le mot de passe est obligatoire")
    private String password;

}
