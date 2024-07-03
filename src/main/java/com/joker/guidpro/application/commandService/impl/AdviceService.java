package com.joker.guidpro.application.commandService.impl;

import com.joker.guidpro.application.commandService.interfaces.AdviceServiceInter;
import com.joker.guidpro.domains.models.agregates.Advice;
import com.joker.guidpro.domains.models.agregates.Expert;
import com.joker.guidpro.domains.models.agregates.User;
import com.joker.guidpro.domains.models.commandes.advice.AdviceCmd;
import com.joker.guidpro.infrastructure.repositories.AdviceRepo;
import com.joker.guidpro.infrastructure.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class AdviceService implements AdviceServiceInter {

    private final AdviceRepo adviceRepo;
    private final ModelMapper modelMapper;
    private final UserRepository userRepo;


    public Advice createAdvice(AdviceCmd adviceCmd, Principal principal) {

        Advice advice = modelMapper.map(adviceCmd, Advice.class);
        User user = userRepo.findByKeycloakId(principal.getName());
        advice.setPublisher((Expert) user);
        return adviceRepo.save(advice);
    }

    public List<Advice> getUserAdvice(Principal principal) {
        User user = userRepo.findByKeycloakId(principal.getName());
        return adviceRepo.findAllByPublisher((Expert) user);
    }

}
