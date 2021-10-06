package com.lasa.security.model;

import lombok.Builder;
import lombok.Data;

@Data
public class CookieInfo {
    private int maxAge;
    private boolean secure;
    private String sameSite;

    @Builder
    public CookieInfo(int maxAge) {
        this.maxAge = maxAge;
        this.secure = true;
        this.sameSite = "none";
    }
}
