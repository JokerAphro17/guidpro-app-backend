package com.joker.guidpro.config;

import com.joker.guidpro.application.outboundService.impl.KeycloakUserServiceImpl;
import com.joker.guidpro.domains.models.agregates.Admin;
import com.joker.guidpro.domains.models.agregates.Expert;
import com.joker.guidpro.domains.models.agregates.Novice;
import com.joker.guidpro.domains.models.enums.UserSatus;
import com.joker.guidpro.infrastructure.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Intializer implements ApplicationRunner {

    private final KeycloakUserServiceImpl keycloakUserService;
    private final UserRepository userRepository;
    private final String password = "password";
    @Override
    public void run(org.springframework.boot.ApplicationArguments args) throws Exception {

        if (userRepository.count() > 0) {
            return;
        }
        Expert expert = new Expert();
        expert.setLastName("Abou El Naga");
        expert.setFirstName("Expert");
        expert.setEmail("expert@guid-pro.com");
        expert.setPhone("01000000000");
        expert.setStatus(UserSatus.ACTIVE);
        expert.setCompanyName("Guid-Pro");
        expert.setCompanyAddress("Cairo, Egypt");
        expert.setCompanyPhone("01000000000");
        expert.setCompanyEmail("expert@guid-pro.com");
        expert.setCompanyWebsite("www.guid-pro.com");
        expert.setCompanyLogo("logo.png");
        expert.setCompanyDescription("Guid-Pro is a company that provides guidance services");
        expert.setCompanyFacebook("www.facebook.com/guid-pro");
        expert.setCompanyTwitter("www.twitter.com/guid-pro");
        expert.setCompanyLinkedin("www.linkedin.com/guid-pro");
        expert.setCompanyInstagram("www.instagram.com/guid-pro");
        expert.setCompanyYoutube("www.youtube.com/guid-pro");
        expert.setCompanyWhatsapp("www.whatsapp.com/guid-pro");

        String keycloakId = keycloakUserService.createUser(expert, password);
        expert.setKeycloakId(keycloakId);
        keycloakUserService.assignRole(keycloakId, "EXPERT");
        userRepository.save(expert);
        // create Novice
        Novice novice = new Novice();
        novice.setLastName("Rachid");
        novice.setFirstName("Ahmed Novice");
        novice.setEmail("novice@guid-pro.com");
        novice.setPhone("01000000000");
        novice.setStatus(UserSatus.ACTIVE);
        novice.setKeycloakId(keycloakUserService.createUser(novice, password));
        keycloakUserService.assignRole(novice.getKeycloakId(), "NOVICE");
        userRepository.save(novice);
        // create Admin
        Admin admin = new Admin();
        admin.setLastName("Souleymane");
        admin.setFirstName("ILBOUDO");
        admin.setEmail("admin@guid-pro.com");
        admin.setPhone("01000000000");
        admin.setStatus(UserSatus.ACTIVE);
        admin.setKeycloakId(keycloakUserService.createUser(admin, password));
        keycloakUserService.assignRole(admin.getKeycloakId(), "ADMIN");
        userRepository.save(admin);

    }
}