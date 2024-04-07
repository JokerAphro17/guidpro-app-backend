package com.joker.guidpro.config;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
@Configuration

public class Beans {


    @Value("${keycloak.adminClientId}")
    private String adminClientId;

    @Value("${keycloak.adminClientSecret}")
    private String adminClientSecret;

    @Value("${keycloak.urls.auth}")
    private String authServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Bean
    public Keycloak keycloak(){


        return KeycloakBuilder.builder()
                .serverUrl(authServerUrl)
                .realm(realm)
                .grantType("client_credentials")
                .clientId(adminClientId)
                .clientSecret(adminClientSecret)
                .build();
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
