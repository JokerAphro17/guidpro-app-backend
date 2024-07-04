package com.joker.guidpro.infrastructure.repositories;

import com.joker.guidpro.domains.models.entities.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DomainRepo extends JpaRepository<Domain, UUID> {

}
