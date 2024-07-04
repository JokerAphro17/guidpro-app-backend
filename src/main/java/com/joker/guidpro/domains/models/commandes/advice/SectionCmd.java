package com.joker.guidpro.domains.models.commandes.advice;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SectionCmd {
    @NotBlank(message = "le titre est obligatoire")
        private String title;

        @NotBlank(message = "la description est obligatoire")
        private String description;

        @NotBlank(message = "le contenu est obligatoire")
        private String content;
}
