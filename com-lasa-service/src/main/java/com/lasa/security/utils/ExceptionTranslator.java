package com.lasa.security.utils;

import com.lasa.security.model.AuthenticationResponse;
import com.lasa.security.utils.ExceptionUtils.TokenInvalidException;
import com.lasa.security.utils.ExceptionUtils.UserAccountException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionTranslator {


    @ExceptionHandler(value = {UserAccountException.class, UnsupportedJwtException.class, MalformedJwtException.class, SignatureException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public AuthenticationResponse processUserAccountException(RuntimeException e, HttpServletRequest request) {
        return AuthenticationResponse.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .error(e.getClass().getSimpleName())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(value = {TokenInvalidException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public AuthenticationResponse processTokenException(RuntimeException e, HttpServletRequest request) {
        return AuthenticationResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .error(e.getClass().getSimpleName())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }


}
