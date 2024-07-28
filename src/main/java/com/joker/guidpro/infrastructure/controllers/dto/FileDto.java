package com.joker.guidpro.infrastructure.controllers.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileDto {

    @NotNull(message = "le fichier est obligatoire")
    private MultipartFile file;

    private String name;

    private String description;

    private String type;


}
