package com.joker.guidpro.config;

import com.joker.guidpro.application.outboundService.impl.KeycloakUserServiceImpl;
import com.joker.guidpro.domains.models.agregates.Admin;
import com.joker.guidpro.domains.models.agregates.Expert;
import com.joker.guidpro.domains.models.agregates.Novice;
import com.joker.guidpro.domains.models.entities.Domain;
import com.joker.guidpro.domains.models.enums.UserSatus;
import com.joker.guidpro.infrastructure.repositories.DomainRepo;
import com.joker.guidpro.infrastructure.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Intializer implements ApplicationRunner {

    private final KeycloakUserServiceImpl keycloakUserService;
    private final UserRepository userRepository;
    private final String password = "password";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final DomainRepo domainRepo;
    @Override
    public void run(org.springframework.boot.ApplicationArguments args) throws Exception {

        if (userRepository.count() == 0) {
            initUsers();
        }
        if (domainRepo.count() == 0) {
            initDomains();
        }




    }

    public void initUsers() {

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
        expert.setPassword(bCryptPasswordEncoder.encode(password));
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
        novice.setPassword(bCryptPasswordEncoder.encode(password));
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
        admin.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(admin);
    }

    public void initDomains() {
        Domain  domain = new Domain();
        domain.setName("Culture");
        domain.setDescription("Culture des plantes");
        domainRepo.save(domain);
        Domain  domain1 = new Domain();
        domain1.setName("Elevage");
        domain1.setDescription("Elevages des animaux");
        domainRepo.save(domain1);
        Domain  domain2 = new Domain();
        domain2.setName("Pêche");
        domain2.setDescription("Pêche");
        domainRepo.save(domain2);
        Domain  domain3 = new Domain();
        domain3.setName("Transformation agricole");
        domain3.setDescription("Transformation des produits agricoles");
        domainRepo.save(domain3);
        Domain  domain4 = new Domain();
        domain4.setName("Commercialisation des produits agricoles");
        domain4.setDescription("Commercialisation des produits agricoles");
        domainRepo.save(domain4);
        Domain  domain5 = new Domain();
        domain5.setName("Horticulture");
        domain5.setDescription("Horticulture");
        domainRepo.save(domain5);
        Domain  domain6 = new Domain();
        domain6.setName("Apiculture");
        domain6.setDescription("Apiculture");
        domainRepo.save(domain6);

    }

}