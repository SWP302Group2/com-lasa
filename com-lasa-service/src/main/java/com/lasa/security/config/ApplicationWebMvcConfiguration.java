package com.lasa.security.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpMethod.*;

public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders(
                        CONTENT_TYPE,
                        CONTENT_LENGTH,
                        HOST,
                        USER_AGENT,
                        ACCEPT,
                        ACCEPT_ENCODING,
                        CONNECTION,
                        AUTHORIZATION
                )
                .allowedOrigins(
                        "http://localhost:3000",
                        "http://localhost:5000",
                        "https://lasa-fpt.web.app",
                        "*"
                )
                .allowedMethods(
                        POST.name(),
                        GET.name(),
                        PUT.name(),
                        DELETE.name(),
                        OPTIONS.name()
                );
    }
}
