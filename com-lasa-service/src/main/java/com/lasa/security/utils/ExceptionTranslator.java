package com.lasa.security.utils;

import com.lasa.security.model.ResponseObject;
import com.lasa.security.utils.ExceptionUtils.TokenException;
import com.lasa.security.utils.ExceptionUtils.UserAccountException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;

@RestControllerAdvice
public class ExceptionTranslator {


    @ExceptionHandler(value = {UserAccountException.class, BadCredentialsException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseObject processForbiddenResponse(RuntimeException e, HttpServletRequest request) {
        System.out.println(e.getMessage());
        return ResponseObject.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .error(e.getClass().getSimpleName())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(value = {TokenException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseObject processTokenException(RuntimeException e, HttpServletRequest request) {
        System.out.println(e.getMessage());
        return ResponseObject.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .error(e.getClass().getSimpleName())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(value = {UnsupportedJwtException.class, MalformedJwtException.class, SignatureException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseObject proccessInvalidTokenException(Exception e,HttpServletRequest request) {
        System.out.println(e.getMessage());
        return ResponseObject.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .error("TokenException")
                .message("INVALID_TOKEN")
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseObject processQueryException(Exception e, HttpServletRequest request) {
        System.out.println(e.getMessage());
        return ResponseObject.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error("InvalidArgumentException")
                .message("INVALID_ARGUMENT")
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseObject processUnknownException(Exception e, HttpServletRequest request) {
        System.out.println(e.getMessage());
        return ResponseObject.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpServerErrorException.InternalServerError.class.getSimpleName())
                .message("UNKNOWN_ERROR")
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(value = {GeneralSecurityException.class, IOException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseObject processGoogleException(Exception e, HttpServletRequest request) {
        System.out.println(e.getMessage());
        return ResponseObject.builder()
                .status(HttpStatus.NOT_ACCEPTABLE.value())
                .error("GoogleException")
                .message("GOOGLE_ACCOUNT_ERROR")
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(value = {ExceptionUtils.EmailDomainException.class, ExceptionUtils.UserAlreadyExistException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseObject processEmailDomainException(Exception e, HttpServletRequest request) {
        System.out.println(e.getMessage());
        return ResponseObject.builder()
                .status(HttpStatus.NOT_ACCEPTABLE.value())
                .error(e.getClass().getSimpleName())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }

}
