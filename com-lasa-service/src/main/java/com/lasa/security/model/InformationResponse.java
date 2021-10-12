package com.lasa.security.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class InformationResponse {
    private Object information;
    private String role;
    private CookieInfo cookieInfo;
    private String accessToken;
    private String refreshToken;
    private String email;
    private String name;
    private String googleToken;
    private String avatarUrl;
}
