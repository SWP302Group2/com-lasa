package com.lasa.security.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleAuthenticationRequest {
    private String token;
    private String name;
    private String avatarUrl;
    private String mssv;
    private String meetUrl;
}
