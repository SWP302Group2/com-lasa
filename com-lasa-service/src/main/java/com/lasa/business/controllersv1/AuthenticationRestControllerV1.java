package com.lasa.business.controllersv1;

import com.lasa.business.controllers.AuthenticationOperations;
import com.lasa.business.services.AuthenticationService;
import com.lasa.business.servicesv1.AuthenticationServiceImplV1;
import com.lasa.security.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/authentication")
@CrossOrigin(
        allowCredentials = "true",
        origins = {"http://localhost:3000", "http://localhost:5500", "https://lasa-fpt.web.app", "http://localhost:8080"},
        allowedHeaders = {
                CONTENT_TYPE,
                CONTENT_LENGTH,
                HOST,
                USER_AGENT,
                ACCEPT,
                ACCEPT_ENCODING,
                CONNECTION,
                AUTHORIZATION
        },
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS}
)
public class AuthenticationRestControllerV1 implements AuthenticationOperations {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationRestControllerV1(@Qualifier("AuthenticationServiceImplV1") AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public ResponseEntity<?> loginAuthentication(AuthenticationRequest authenticationRequest,
                                                 HttpServletResponse response,
                                                 HttpServletRequest request) {
        try {
            String accessToken = authenticationService.authenticateUsernameAndPassword(authenticationRequest);

            return ResponseEntity.status(OK)
                    .body(InformationResponse.builder()
                            .accessToken(accessToken)
                            .cookieInfo(CookieInfo.builder()
                                    .maxAge(7 * 24 * 60 * 60)
                                    .build())
                            .build());
        }catch (BadCredentialsException ex) {

            return ResponseEntity.status(UNAUTHORIZED)
                    .body(AuthenticationResponse.builder()
                            .status(UNAUTHORIZED.value())
                            .error(ex.getClass().getSimpleName())
                            .message(ex.getMessage())
                            .path(request.getRequestURI())
                            .build()
                    );
        }

    }

    @Override
    public ResponseEntity<?> googleAuthentication(GoogleAuthenticationRequest authenticationRequest,
                                                  String role,
                                                  HttpServletResponse response,
                                                  HttpServletRequest request) {

        try {
            String accessToken = authenticationService.authenticateGoogleAccount(authenticationRequest, role);
            if(accessToken == null) {
                return ResponseEntity.status(UNAUTHORIZED)
                        .body("Account not found");
            }else {

                return ResponseEntity.status(OK)
                        .body(InformationResponse.builder()
                                .accessToken(accessToken)
                                .cookieInfo(CookieInfo.builder()
                                        .maxAge(7 * 24 * 60 * 60)
                                        .build())
                                .build());
            }
        } catch (GeneralSecurityException | IOException ex) {

            return ResponseEntity.status(NOT_ACCEPTABLE)
                    .body(AuthenticationResponse.builder()
                            .status(NOT_ACCEPTABLE.value())
                            .error(ex.getClass().getSimpleName())
                            .message(ex.getMessage())
                            .path(request.getRequestURI())
                            .build()
                            );
        } catch (AuthenticationServiceImplV1.EmailDomainException ex) {

            return ResponseEntity.status(UNAUTHORIZED)
                    .body(AuthenticationResponse.builder()
                            .status(UNAUTHORIZED.value())
                            .error(ex.getClass().getSimpleName())
                            .message(ex.getMessage())
                            .path(request.getRequestURI())
                            .build()
                    );
        }
    }


}

