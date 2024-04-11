package com.joker.guidpro.domains.models.commandes.users;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangePasswordCmd {
    @NotBlank(message = "Old password is required")
    private String oldPassword;
    @NotBlank(message = "New password is required")
    private String newPassword;
    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;

    public boolean isPasswordMatch() {
        return newPassword.equals(confirmPassword);
    }

}
