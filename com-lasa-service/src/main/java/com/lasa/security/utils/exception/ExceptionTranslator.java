package com.lasa.security.utils.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.lasa.security.utils.exception.ExceptionUtils.DeleteException;
import com.lasa.security.utils.exception.ExceptionUtils.TokenException;
import com.lasa.security.utils.exception.ExceptionUtils.UserAccountException;
import com.lasa.security.utils.model.ResponseObject;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;

@RestControllerAdvice
public class ExceptionTranslator {

    private final Logger LOGGER = LogManager.getLogger(ExceptionTranslator.class);

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseObject processUnknownException(Exception e, HttpServletRequest request) {
        LOGGER.error(e.getMessage());
        return ResponseObject.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpServerErrorException.InternalServerError.class.getSimpleName())
                .message("UNKNOWN_ERROR")
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(value = {UserAccountException.class, BadCredentialsException.class, AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseObject processForbiddenResponse(RuntimeException e, HttpServletRequest request) {
        LOGGER.warn(e.getMessage());
        LOGGER.info(e.getMessage());
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
        LOGGER.info(e.getMessage());
        return ResponseObject.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .error(e.getClass().getSimpleName())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(value = {UnsupportedJwtException.class, MalformedJwtException.class, SignatureException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseObject processInvalidTokenException(Exception e,HttpServletRequest request) {
        LOGGER.info(e.getMessage());
        System.out.println(e.getMessage());
        return ResponseObject.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .error(TokenException.class.getSimpleName())
                .message("INVALID_TOKEN")
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class, MismatchedInputException.class, JsonParseException.class, HttpMessageNotReadableException.class, DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseObject processQueryException(Exception e, HttpServletRequest request) {
        LOGGER.info(e.getMessage());
        return ResponseObject.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error("InvalidArgumentException")
                .message("INVALID_ARGUMENT")
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class, EmptyResultDataAccessException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseObject processNotFoundResponse(Exception e, HttpServletRequest request) {
        LOGGER.info(e.getMessage());
        return ResponseObject.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(e.getClass().getSimpleName())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(value = {GeneralSecurityException.class, IOException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseObject processGoogleException(Exception e, HttpServletRequest request) {
        LOGGER.info(e.getMessage());
        return ResponseObject.builder()
                .status(HttpStatus.NOT_ACCEPTABLE.value())
                .error("GoogleException")
                .message("GOOGLE_ACCOUNT_ERROR")
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(value = {ExceptionUtils.EmailDomainException.class, ExceptionUtils.UserAlreadyExistException.class, UsernameNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseObject processEmailDomainException(Exception e, HttpServletRequest request) {
        LOGGER.info(e.getMessage());
        return ResponseObject.builder()
                .status(HttpStatus.NOT_ACCEPTABLE.value())
                .error(e.getClass().getSimpleName())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(value = {ExceptionUtils.ArgumentException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseObject processOverflowException(Exception e, HttpServletRequest request) {
        LOGGER.info(e.getMessage());
        System.out.println(e.getMessage());
        return ResponseObject.builder()
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .error(e.getClass().getSimpleName())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(value = {ExceptionUtils.DuplicatedException.class, ExceptionUtils.EmailSenderException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseObject processConflictException(Exception e, HttpServletRequest request) {
        LOGGER.info(e.getMessage());
        return ResponseObject.builder()
                .status(HttpStatus.CONFLICT.value())
                .error(e.getClass().getSimpleName())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(value = {ConstraintViolationException.class, MethodArgumentNotValidException.class, BindException.class, DeleteException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseObject processConstraintViolationException(Exception ex, HttpServletRequest request) {
        LOGGER.info(ex.getMessage());
        HashMap<String, String> errors = new HashMap<>();
        if(ex instanceof ConstraintViolationException) {
            ((ConstraintViolationException) ex).getConstraintViolations().stream()
                    .forEach(t -> errors.put(t.getPropertyPath().toString(), t.getMessage()));
        }

        if(ex instanceof BindException) {
            ((BindException) ex).getFieldErrors().stream()
                    .forEach(t -> errors.put(t.getField(), t.getDefaultMessage()));
        }

        if(ex instanceof MethodArgumentNotValidException) {
            ((MethodArgumentNotValidException)ex).getAllErrors().stream()
                    .forEach(t -> {
                        if(!t.getCode().equals("NotNull") && !t.getCode().equals("NotEmpty"))
                            errors.put(t.getCode(), t.getDefaultMessage());
                    });
        }

        if(ex instanceof DeleteException) {
            errors.put(DeleteException.class.getSimpleName(), ex.getMessage());
        }

        return ResponseObject.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .path(request.getRequestURI())
                .build();
    }



}
