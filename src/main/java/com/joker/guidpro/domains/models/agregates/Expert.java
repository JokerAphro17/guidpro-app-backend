package com.joker.guidpro.domains.models.agregates;


import com.joker.guidpro.domains.models.entities.Domain;
import com.joker.guidpro.domains.models.valueObjects.UserRoles;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@DiscriminatorValue("EXPERT")
@Data
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
}
