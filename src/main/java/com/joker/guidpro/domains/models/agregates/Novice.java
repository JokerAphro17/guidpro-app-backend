package com.joker.guidpro.domains.models.agregates;

import com.joker.guidpro.domains.models.entities.Domain;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Set;

@Entity
@DiscriminatorValue("NOVICE")
@Data
public class Novice extends User {

    @ManyToMany
    private Set<Domain> interestedDomains;

    @ManyToMany
    private Set<Advice> savedAdvices;
}

