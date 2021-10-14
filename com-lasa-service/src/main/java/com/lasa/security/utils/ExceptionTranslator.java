package com.lasa.security.utils;

import com.lasa.security.model.AuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public AuthenticationResponse processRuntimeException(RuntimeException e, HttpServletRequest request) {
        return AuthenticationResponse.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .error(e.getClass().getSimpleName())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }
}
