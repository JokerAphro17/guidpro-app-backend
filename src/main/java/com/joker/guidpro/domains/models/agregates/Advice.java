package com.joker.guidpro.domains.models.agregates;

import com.joker.guidpro.domains.models.entities.Domain;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;


@Entity
@Data
@EnableJpaAuditing
public class Advice implements Serializable {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private UUID id;

    private String title;

    private String content;


    private boolean isDeleted;

    private String archivedAt;

    private boolean isArchived;


    // relationships

    @OneToOne
    private Expert publisher;

    @ManyToMany
    private Set<Domain> domains;



    @ManyToMany
    private Set<Novice> usersInterested;
}
