package com.joker.guidpro.domains.models.agregates;


import com.joker.guidpro.domains.models.entities.Domain;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Entity
@DiscriminatorValue("EXPERT")
@Data
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
public class Expert extends User {


    @OneToOne
    private Domain domain;

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
