package com.joker.guidpro.infrastructure.controllers;

import com.joker.guidpro.application.commandService.impl.AdviceService;
import com.joker.guidpro.domains.models.agregates.Advice;
import com.joker.guidpro.domains.models.commandes.advice.AdviceCmd;
import com.joker.guidpro.infrastructure.controllers.dto.ResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/advices")
public class AdviceController {

    private final AdviceService adviceService;


    public AdviceController(AdviceService adviceService) {
        this.adviceService = adviceService;
    }


    @GetMapping
    public String getAdvices() {
        return "advices";
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createAdvice(@Valid @RequestBody AdviceCmd adviceCmd, Principal principal) {
        Advice advice = adviceService.createAdvice(adviceCmd, principal);
        return ResponseEntity.ok(new ResponseDTO("Advice created successfully", advice, true));
    }

    // get advice by user
    @GetMapping("/by-user")
    public ResponseEntity<ResponseDTO> getUserAdvice(Principal principal) {
        return ResponseEntity.ok(new ResponseDTO("User advice", adviceService.getUserAdvice(principal), true));
    }


}
