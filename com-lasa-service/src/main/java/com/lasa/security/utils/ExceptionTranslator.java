package com.lasa.security.utils;

import com.lasa.security.model.AuthenticationResponse;
import jdk.nashorn.internal.ir.RuntimeNode;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ErrorMessages;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler(ExceptionUtils.TokenInvalidException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public AuthenticationResponse processTokenInvalidException(RuntimeException e, HttpServletRequest request) {
        return AuthenticationResponse.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .error(e.getClass().getSimpleName())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(ExceptionUtils.UserAccountException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public AuthenticationResponse processUserAccountException(RuntimeException e, HttpServletRequest request) {
        return AuthenticationResponse.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .error(e.getClass().getSimpleName())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }
}
