package com.joker.guidpro.domains.models.agregates;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joker.guidpro.domains.models.enums.UserSatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "user_role")
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
public  abstract class User implements Serializable {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private UUID id;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String firstName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String keycloakId;

    private  String phone;

    private boolean isDeleted;

    @Column(nullable = false)
    private String role = this.getClass().getSimpleName().toUpperCase();

    @JsonIgnore
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserSatus status;




}