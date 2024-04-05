package com.joker.guidpro.domains.models.entities;

import com.joker.guidpro.domains.models.agregates.Advice;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@EnableJpaAuditing
public class Domain implements Serializable {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private UUID id;

    private String name;

    private String description;


    @ManyToMany
    Set<Advice> advices;

    private boolean isDeleted;
}
