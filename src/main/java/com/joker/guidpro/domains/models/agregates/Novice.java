package com.joker.guidpro.domains.models.agregates;

import com.joker.guidpro.domains.models.entities.Domain;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Set;

@Entity
@DiscriminatorValue("NOVICE")
@Data
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
public class Novice extends User {

    @ManyToMany
    private Set<Domain> interestedDomains;

    @ManyToMany
    private Set<Advice> savedAdvices;
}

