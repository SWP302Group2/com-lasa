package com.lasa.business.controllersv1;

import com.lasa.business.controllers.AuthenticationOperations;
import com.lasa.business.services.AuthenticationService;
import com.lasa.security.model.*;
import com.lasa.security.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/authentication")
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
            String accessToken = authenticationService.authenticateUsernameAndPassword(authenticationRequest);
            return ResponseEntity.status(OK)
                    .body(InformationResponse.builder()
                            .accessToken(accessToken)
                            .build());
    }

    @Override
    public ResponseEntity<?> googleAuthentication(GoogleAuthenticationRequest authenticationRequest,
                                                  String role,
                                                  HttpServletResponse response,
                                                  HttpServletRequest request) throws GeneralSecurityException, IOException, ExceptionUtils.EmailDomainException {

            String accessToken = authenticationService.authenticateGoogleAccount(authenticationRequest, role);
            if(accessToken == null) {
                return ResponseEntity.status(UNAUTHORIZED)
                        .body(ResponseObject.builder()
                        .status(UNAUTHORIZED.value())
                        .message("ACCOUNT_NOT_FOUND")
                        .path(request.getRequestURI())
                        .build());
            }else {
                return ResponseEntity.status(OK)
                        .body(InformationResponse.builder()
                                .accessToken(accessToken)
                                .build());
            }

    }

    @Override
    public ResponseEntity<?> emailRecognition(GoogleAuthenticationRequest authenticationRequest, HttpServletResponse response, HttpServletRequest request) throws GeneralSecurityException, IOException, ExceptionUtils.EmailDomainException, ExceptionUtils.UserAlreadyExistException {
            InformationResponse informationResponse = authenticationService.emailVerify(authenticationRequest);
            return ResponseEntity.ok(informationResponse);
    }


}

