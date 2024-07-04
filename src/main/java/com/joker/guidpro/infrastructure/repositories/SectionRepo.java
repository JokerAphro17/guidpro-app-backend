package com.joker.guidpro.infrastructure.repositories;

import com.joker.guidpro.domains.models.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SectionRepo extends JpaRepository<Section, UUID> {
}