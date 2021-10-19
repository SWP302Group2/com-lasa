package com.lasa.security.utils.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiIgnore
public class InformationResponse {
    private Object information;
    private String role;
    private String accessToken;
    private String refreshToken;
    private String email;
    private String name;
    private String googleToken;
    private String avatarUrl;
}
