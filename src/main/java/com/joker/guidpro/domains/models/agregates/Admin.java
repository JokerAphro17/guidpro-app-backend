package com.joker.guidpro.domains.models.agregates;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.Serializable;


@Entity
@DiscriminatorValue("ADMIN")
@Data
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
public class Admin extends User {


}
