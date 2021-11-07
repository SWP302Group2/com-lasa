package com.lasa.business.services;

import com.lasa.security.utils.model.AuthenticationRequest;
import com.lasa.security.utils.model.GoogleAuthenticationRequest;
import com.lasa.security.utils.model.InformationResponse;
import com.lasa.security.utils.exception.ExceptionUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
public interface AuthenticationService {

    String authenticateUsernameAndPassword(AuthenticationRequest jwt) throws BadCredentialsException;

    String authenticateGoogleAccount(GoogleAuthenticationRequest googleAuthenticationRequest, String role) throws GeneralSecurityException, IOException, ExceptionUtils.EmailDomainException;

    InformationResponse emailVerify(GoogleAuthenticationRequest googleAuthenticationRequest) throws GeneralSecurityException, IOException, ExceptionUtils.EmailDomainException, ExceptionUtils.UserAlreadyExistException;
}
