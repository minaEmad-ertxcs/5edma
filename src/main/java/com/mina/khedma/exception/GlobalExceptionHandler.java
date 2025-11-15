package com.mina.khedma.exception;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ErrorResponseException(HttpStatus.NOT_FOUND, ex.getCause());
    }

    @ExceptionHandler(BadRequestException.class)
    public ErrorResponse handleBadRequestException(BadRequestException ex) {
        return new ErrorResponseException(HttpStatus.BAD_REQUEST, ex.getCause());
    }

//    @ExceptionHandler(BadCredentialsException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ErrorResponse handleBadCredentialsException(BadCredentialsException ex) {
//        return new ErrorResponseException(HttpStatus.UNAUTHORIZED, ex.getCause());
//    }
}
