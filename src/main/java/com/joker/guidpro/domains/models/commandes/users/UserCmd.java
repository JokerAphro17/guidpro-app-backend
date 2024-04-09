package com.joker.guidpro.domains.models.commandes.users;

import com.joker.guidpro.domains.models.validations.OnCreate;
import com.joker.guidpro.domains.models.validations.OnUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import lombok.Data;

@Data
public class UserCmd {
    @NotEmpty(message = "Le nom est obligatoire")
    private String lastName;
    @NotEmpty(message = "Le prénom est obligatoire")
    private String firstName;
    @NotEmpty(message = "L'email est obligatoire", groups = {OnCreate.class})
    @Email(message = "L'email doit être valide", groups = {OnCreate.class})
    @Null(message = "L'email ne peut pas être modifié", groups = {OnUpdate.class})
    private String email;
    private String phone;
    @NotEmpty(message = "Le role est obligatoire")
    @Null(message = "Le role ne peut pas être modifié", groups = {OnUpdate.class})
    private String role;

    private String CompanyName;

    private String CompanyAddress;

    private String CompanyPhone;

    private String CompanyEmail;

    private String CompanyWebsite;

    private String CompanyLogo;

    private String CompanyDescription;

    private String CompanyLocation;

    private String CompanyCity;

    private String CompanyCountry;

    private String CompanyFacebook;

    private String CompanyTwitter;

    private String CompanyLinkedin;

    private String CompanyInstagram;

    private String CompanyYoutube;

    private String CompanyTiktok;

    private String CompanyWhatsapp;
}
