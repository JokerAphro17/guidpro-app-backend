package com.joker.guidpro.application.commandService.impl;

import com.joker.guidpro.domains.models.entities.Domain;
import com.joker.guidpro.infrastructure.repositories.DomainRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DomainService {
    private final DomainRepo domainRepo;

    public List<Domain> getAllDomains() {
        return domainRepo.findAll();
    }
}
