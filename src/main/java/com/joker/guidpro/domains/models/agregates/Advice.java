package com.joker.guidpro.domains.models.agregates;

import com.joker.guidpro.domains.models.entities.Domain;
import com.joker.guidpro.domains.models.entities.Section;
import com.joker.guidpro.domains.models.valueObjects.AdviceStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
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

    private  long budget;

    private String title;

    private String content;

    private String coverUrl;


    private boolean isDeleted;

    private String archivedAt;

    private boolean isArchived;


    @Enumerated(EnumType.STRING)
    private AdviceStatus status = AdviceStatus.PENDING;


    // relationships

    @OneToOne
    private Expert publisher;

    @ManyToMany
    private Set<Domain> domains;

    @OneToMany
    private Set<Section> sections;

    @ManyToMany
    private Set<Novice> usersInterested;

    private String createdAt;

    private String updatedAt;

    private String publishedAt;


    @PrePersist
    public void prePersist() {
        this.createdAt = String.valueOf(LocalDateTime.now());
        this.updatedAt = String.valueOf(LocalDateTime.now());

    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = String.valueOf(LocalDateTime.now());
    }
}
