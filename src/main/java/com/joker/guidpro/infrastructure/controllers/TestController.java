package com.joker.guidpro.infrastructure.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/admins")
    public String getAdmins() {
        return "Admins";
    }


    @GetMapping("/experts")
    public String getExperts() {
        return "Experts";
    }

    @GetMapping("/novices")
    public String getNovices() {
        return "Novices";
    }


}
