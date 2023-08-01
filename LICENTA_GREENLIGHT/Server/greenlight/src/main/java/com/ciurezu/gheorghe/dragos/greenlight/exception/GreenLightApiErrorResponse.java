package com.ciurezu.gheorghe.dragos.greenlight.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

//@RequiredArgsConstructor
//@Getter
public record GreenLightApiErrorResponse(String message, HttpStatus httpStatus, ZonedDateTime zonedDateTime) {

}
