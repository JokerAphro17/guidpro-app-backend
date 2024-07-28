package com.joker.guidpro.infrastructure.repositories;

import com.joker.guidpro.domains.models.agregates.Advice;
import com.joker.guidpro.domains.models.agregates.Expert;
import com.joker.guidpro.domains.models.valueObjects.AdviceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AdviceRepo extends JpaRepository<Advice, UUID>{


    List<Advice> findAllByPublisher(Expert expert);

    List<Advice> findAllByStatus(AdviceStatus status);

}
