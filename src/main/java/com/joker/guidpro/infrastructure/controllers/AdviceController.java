package com.joker.guidpro.infrastructure.controllers;

import com.joker.guidpro.application.commandService.impl.AdviceService;
import com.joker.guidpro.domains.models.agregates.Advice;
import com.joker.guidpro.domains.models.commandes.advice.AdviceCmd;
import com.joker.guidpro.domains.models.commandes.advice.SectionCmd;
import com.joker.guidpro.infrastructure.controllers.dto.ResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

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


    // update advice
    @PutMapping("/{adviceId}")
    public ResponseEntity<ResponseDTO> updateAdvice(@PathVariable UUID adviceId, @Valid @RequestBody AdviceCmd adviceCmd) {
        return ResponseEntity.ok(new ResponseDTO("Advice updated successfully", adviceService.updateAdvice(adviceId, adviceCmd), true));
    }

    @GetMapping("/{adviceId}")
    public ResponseEntity<ResponseDTO> getAdvice(@PathVariable UUID adviceId) {
        return ResponseEntity.ok(new ResponseDTO("Advice", adviceService.getAdvice(adviceId), true));
    }

    // get advice by user
    @GetMapping("/by-user")
    public ResponseEntity<ResponseDTO> getUserAdvice(Principal principal) {
        return ResponseEntity.ok(new ResponseDTO("User advice", adviceService.getUserAdvice(principal), true));
    }

    @PostMapping("/{adviceId}/sections")
    public ResponseEntity<ResponseDTO> addSectionToAdvice(@PathVariable UUID adviceId, @Valid @RequestBody SectionCmd sectionCmd) {
        return ResponseEntity.ok(new ResponseDTO("Section added successfully", adviceService.addSectionToAdvice(adviceId, sectionCmd), true));
    }

    // update section
    @PutMapping("/sections/{sectionId}")
    public ResponseEntity<ResponseDTO> updateSection(@PathVariable UUID sectionId, @Valid @RequestBody SectionCmd sectionCmd) {
        return ResponseEntity.ok(new ResponseDTO("Section updated successfully", adviceService.updateSection(sectionId, sectionCmd), true));
    }


    // publish advice
    @PutMapping("/{adviceId}/publish")
    public ResponseEntity<ResponseDTO> publishAdvice(@PathVariable UUID adviceId) {
        return ResponseEntity.ok(new ResponseDTO("Advice published successfully", adviceService.publishAdvice(adviceId), true));
    }

    @PutMapping("/{adviceId}/unpublish")
    public ResponseEntity<ResponseDTO> unpublishAdvice(@PathVariable UUID adviceId) {
        return ResponseEntity.ok(new ResponseDTO("Advice unpublished successfully", adviceService.archiveAdvice(adviceId), true));
    }



}
