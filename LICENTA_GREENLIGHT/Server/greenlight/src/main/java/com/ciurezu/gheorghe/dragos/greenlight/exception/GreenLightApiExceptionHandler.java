package com.ciurezu.gheorghe.dragos.greenlight.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GreenLightApiExceptionHandler {

    @ExceptionHandler(value = {UsernameNotFoundException.class} )
    public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException e){
        HttpStatus notFoundStatusCode = HttpStatus.NOT_FOUND;
        GreenLightApiErrorResponse greenLightApiException = new GreenLightApiErrorResponse(
                e.getMessage(),
                notFoundStatusCode,
                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(greenLightApiException,notFoundStatusCode);
    }

    @ExceptionHandler(value = {BadRequestException.class} )
    public ResponseEntity<?> handleBadRequestException(BadRequestException e){
        HttpStatus notFoundStatusCode = HttpStatus.BAD_REQUEST;
        GreenLightApiErrorResponse greenLightApiException = new GreenLightApiErrorResponse(
                e.getMessage(),
                notFoundStatusCode,
                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(greenLightApiException,notFoundStatusCode);
    }

    @ExceptionHandler(value = {ConflictException.class} )
    public ResponseEntity<?> handleConflictException(ConflictException e){
        HttpStatus notFoundStatusCode = HttpStatus.CONFLICT;
        GreenLightApiErrorResponse greenLightApiException = new GreenLightApiErrorResponse(
                e.getMessage(),
                notFoundStatusCode,
                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(greenLightApiException,notFoundStatusCode);
    }

}
