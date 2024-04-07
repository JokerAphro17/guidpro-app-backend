package com.joker.guidpro.application.exceptions.handlers;

import com.joker.guidpro.application.exceptions.*;
import com.joker.guidpro.infrastructure.controllers.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<ResponseDTO> handleAlreadyExist(AlreadyExistsException e){
        ResponseDTO responseDTO = new ResponseDTO(e.getMessage(), null, false);
        return ResponseEntity.status(HttpStatus.FOUND).body(responseDTO);
    }

    @ExceptionHandler(value = LoginFailedException.class)
    public ResponseEntity<ResponseDTO> handleFailedLogin(LoginFailedException exception){
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDTO(exception.getMessage(), null, false));
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ResponseDTO> handleFailedLogin(NotFoundException exception){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(exception.getMessage(), null, false));
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<ResponseDTO> handleFailedLogin(UnauthorizedException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO(exception.getMessage(), null, false));
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ResponseDTO> handleFailedLogin(AccessDeniedException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO(exception.getMessage(), null, false));
    }

    @ExceptionHandler(value = ConflictException.class)
    public ResponseEntity<ResponseDTO> handleFailedLogin(ConflictException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseDTO(exception.getMessage(), null, false));
    }
    

}
