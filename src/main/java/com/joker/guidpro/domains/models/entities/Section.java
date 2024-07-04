package com.joker.guidpro.domains.models.entities;

import com.joker.guidpro.domains.models.agregates.Advice;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Data
@Entity
public class Section {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private UUID id;

    private String title;

    private String description;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToOne
    private Advice advice;
    private boolean isDeleted;

}
