package com.joker.guidpro.domains.models.commandes.advice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class AdviceCmd {

    @NotBlank(message = "le titre est obligatoire")
    private String title;

    @NotBlank(message = "la description est obligatoire")
    private String description;

    @NotNull(message = "le budget est obligatoire")
    private long budget;

    @NotNull(message = "le domaine est obligatoire")
    private String domainId;


}
