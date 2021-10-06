package com.lasa.business.services;

import com.lasa.business.servicesv1.AuthenticationServiceImplV1;
import com.lasa.security.model.AuthenticationRequest;
import com.lasa.security.model.GoogleAuthenticationRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
public interface AuthenticationService {

    String authenticateUsernameAndPassword(AuthenticationRequest jwt) throws BadCredentialsException;

    String authenticateGoogleAccount(GoogleAuthenticationRequest googleAuthenticationRequest, String role) throws GeneralSecurityException, IOException, AuthenticationServiceImplV1.EmailDomainException;

}
