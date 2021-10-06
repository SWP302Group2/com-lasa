package com.lasa.security.model;

import lombok.*;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class AuthenticationResponse {
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Builder
    public AuthenticationResponse(int status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now().format(formatter);
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
