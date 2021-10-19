package com.lasa.security.utils.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Configuration
@ConfigurationProperties(prefix = "application.jwt")
@Getter
@NoArgsConstructor
@Setter
public class JwtConfig {

    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationAfter;

    public String getAuthorizationHeader() {
        return AUTHORIZATION;
    }
}
