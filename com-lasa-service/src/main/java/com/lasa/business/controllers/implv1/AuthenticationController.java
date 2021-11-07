package com.lasa.business.controllers.implv1;

import com.lasa.business.controllers.AuthenticationOperations;
import com.lasa.business.services.AuthenticationService;
import com.lasa.security.utils.model.AuthenticationRequest;
import com.lasa.security.utils.model.GoogleAuthenticationRequest;
import com.lasa.security.utils.model.InformationResponse;
import com.lasa.security.utils.model.ResponseObject;
import com.lasa.security.utils.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequestMapping("/api/v1/authentication")
@ApiIgnore
public class AuthenticationController implements AuthenticationOperations {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(@Qualifier("AuthenticationServiceImplV1") AuthenticationService authenticationService) {
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
    @Transactional
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

