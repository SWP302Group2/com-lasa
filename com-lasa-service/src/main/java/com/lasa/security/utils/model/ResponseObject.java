package com.lasa.security.utils.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Getter
@ApiIgnore
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseObject {
    private String timestamp;
    private int status;
    private String error;
    private HashMap<String, String> errors = new HashMap<>();
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

    public void addError(String error, String message) {
        errors.put(error, message);
    }
}
