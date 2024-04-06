package com.joker.guidpro.domains.models.entities;

import com.joker.guidpro.domains.models.agregates.Novice;
import com.joker.guidpro.domains.models.agregates.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.UUID;
@Data
@Entity
@NoArgsConstructor
@EnableJpaAuditing
public class Comment {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private UUID id;

    @ManyToOne
    private User author;

    private String content;


}
