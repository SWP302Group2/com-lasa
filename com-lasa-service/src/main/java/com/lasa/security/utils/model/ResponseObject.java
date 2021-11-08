package com.lasa.security.utils.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDateTime;
import java.util.HashMap;

@Getter
@ApiIgnore
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseObject {
    private int status;
    private String error;
    private HashMap<String, String> errors;
    private String message;
    private String path;
    private LocalDateTime timestamp;

    @Builder
    public ResponseObject( int status, String error, HashMap<String, String> errors, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.errors = errors;
        this.message = message;
        this.path = path;
    }
}
