package com.lasa.security.utils.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@Data
@NoArgsConstructor
@ApiIgnore
public class AuthenticationRequest {
    private String username;
    private String password;
}
