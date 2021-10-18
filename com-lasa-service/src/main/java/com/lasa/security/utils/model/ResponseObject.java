package com.lasa.security.utils.model;

import lombok.Builder;
import lombok.Getter;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@ApiIgnore
public class ResponseObject {
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Builder
    public ResponseObject(int status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now().format(formatter);
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
