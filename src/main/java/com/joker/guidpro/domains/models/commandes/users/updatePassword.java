package com.joker.guidpro.domains.models.commandes.users;

import lombok.Data;

@Data
public class updatePassword {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
