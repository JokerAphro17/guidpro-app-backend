package com.joker.guidpro.application.commandService.impl;

import com.joker.guidpro.application.commandService.interfaces.AdviceServiceInter;
import com.joker.guidpro.application.exceptions.NotFoundException;
import com.joker.guidpro.application.outboundService.impl.Seafile;
import com.joker.guidpro.domains.models.agregates.Advice;
import com.joker.guidpro.domains.models.agregates.Expert;
import com.joker.guidpro.domains.models.agregates.User;
import com.joker.guidpro.domains.models.commandes.advice.AdviceCmd;
import com.joker.guidpro.domains.models.commandes.advice.SectionCmd;
import com.joker.guidpro.domains.models.entities.Section;
import com.joker.guidpro.domains.models.valueObjects.AdviceStatus;
import com.joker.guidpro.infrastructure.controllers.dto.FileDto;
import com.joker.guidpro.infrastructure.repositories.AdviceRepo;
import com.joker.guidpro.infrastructure.repositories.DomainRepo;
import com.joker.guidpro.infrastructure.repositories.SectionRepo;
import com.joker.guidpro.infrastructure.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AdviceService implements AdviceServiceInter {

    private final AdviceRepo adviceRepo;
    private final ModelMapper modelMapper;
    private final UserRepository userRepo;
    private   final SectionRepo sectionRepo;
    private final DomainRepo domainRepo;
    private final Seafile seafile;


    public Advice createAdvice(AdviceCmd adviceCmd, Principal principal) {

        Advice advice = modelMapper.map(adviceCmd, Advice.class);
        User user = userRepo.findByKeycloakId(principal.getName());
        advice.setPublisher((Expert) user);
        return adviceRepo.save(advice);
    }

    // update advice
    public Advice updateAdvice(UUID adviceId, AdviceCmd adviceCmd) {
        Advice advice = adviceRepo.findById(adviceId).orElseThrow( () -> new NotFoundException("Advice not found"));
        advice.setTitle(adviceCmd.getTitle());
        advice.setDescription(adviceCmd.getDescription());
        advice.setDomain(domainRepo.findById(UUID.fromString(adviceCmd.getDomainId())).orElseThrow( () -> new NotFoundException("Domain not found")));

        return adviceRepo.save(advice);
    }

    public List<Advice> getUserAdvice(Principal principal) {
        User user = userRepo.findByKeycloakId(principal.getName());
        return adviceRepo.findAllByPublisher((Expert) user);
    }

    // show article
    public Advice getAdvice(UUID adviceId) {
        return adviceRepo.findById(adviceId).orElseThrow( () -> new NotFoundException("Advice not found"));
    }

    public Advice addSectionToAdvice(UUID adviceId, SectionCmd sectionCmd) {
        Advice advice = adviceRepo.findById(adviceId).orElseThrow( () -> new NotFoundException("Advice not found"));
        Section section = modelMapper.map(sectionCmd, Section.class);
        section = sectionRepo.save(section);
        advice.addSection(section);
        return adviceRepo.save(advice);
    }

    // update section
    public Section updateSection(UUID sectionId, SectionCmd sectionCmd) {
        Section section = sectionRepo.findById(sectionId).orElseThrow( () -> new NotFoundException("Section not found"));
        modelMapper.map(sectionCmd, section);
        return sectionRepo.save(section);
    }

    // publier un article
    public Advice publishAdvice(UUID adviceId) {
        Advice advice = adviceRepo.findById(adviceId).orElseThrow( () -> new NotFoundException("Advice not found"));
        advice.setStatus(AdviceStatus.PUBLISHED);
        advice.setPublishedAt(LocalDateTime.now().toString());
        return adviceRepo.save(advice);
    }

    // archiver un article
    public Advice archiveAdvice(UUID adviceId) {
        Advice advice = adviceRepo.findById(adviceId).orElseThrow( () -> new NotFoundException("Advice not found"));
        advice.setArchivedAt(LocalDateTime.now().toString());
        advice.setStatus(AdviceStatus.ARCHIVED);
        return adviceRepo.save(advice);
    }

    // update cover
    public String updateCover(UUID adviceId, FileDto fileDto) throws IOException {
        Advice advice = adviceRepo.findById(adviceId).orElseThrow( () -> new NotFoundException("Advice not found"));
        String coverUrl = seafile.uploadFile(fileDto.getFile());
        advice.setCoverUrl(coverUrl);
        adviceRepo.save(advice);

        return coverUrl;
    }

    // retrieve published articles
    public List<Advice> getPublishedAdvice() {
        return adviceRepo.findAllByStatus(AdviceStatus.PUBLISHED);
    }
}
