package com.joker.guidpro.domains.models.commandes.users;

import com.joker.guidpro.domains.models.validations.OnCreate;
import com.joker.guidpro.domains.models.validations.OnUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import lombok.Data;

@Data
public class UpdateUserCmd {
    @NotEmpty(message = "Le nom est obligatoire")
    private String lastName;
    @NotEmpty(message = "Le prénom est obligatoire")
    private String firstName;
    @NotEmpty(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    private String email;
    private String phone;

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
