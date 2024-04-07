package com.joker.guidpro.domains.models.agregates;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.Serializable;


@Entity
@DiscriminatorValue("ADMIN")
@Data
public class Admin extends User {


}
