package com.joker.guidpro.infrastructure.controllers.dto;

import lombok.Data;

@Data
public class ResponseDTO {

    private String message;
    private Object data;
    private boolean success;

    public ResponseDTO(String message, Object data, boolean success) {
        this.message = message;
        this.data = data;
        this.success = success;
    }
}
