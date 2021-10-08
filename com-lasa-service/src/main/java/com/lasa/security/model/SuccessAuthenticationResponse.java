package com.lasa.security.model;

import lombok.Builder;
import lombok.Data;

@Data
public class SuccessAuthenticationResponse {
    private String token;
    private int maxAge;
    private boolean secure;
    private String sameSite;

    @Builder
    public SuccessAuthenticationResponse(int maxAge, String token) {
        this.maxAge = maxAge;
        this.token = token;
        this.secure = true;
        this.sameSite = "none";
    }
}
