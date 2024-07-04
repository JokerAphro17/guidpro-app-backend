package com.joker.guidpro.infrastructure.controllers;


import com.joker.guidpro.application.commandService.impl.DomainService;
import com.joker.guidpro.infrastructure.controllers.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/domains")
public class DomainController {

    private  final DomainService domainService;

    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getDomains() {
        return ResponseEntity.ok(new ResponseDTO("Domains", domainService.getAllDomains(), true));
    }


}
