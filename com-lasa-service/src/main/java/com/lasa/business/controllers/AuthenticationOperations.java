package com.lasa.business.controllers;

import com.lasa.security.model.AuthenticationRequest;
import com.lasa.security.model.GoogleAuthenticationRequest;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/default")
public interface AuthenticationOperations {

    @PostMapping
    public ResponseEntity<?> loginAuthentication(
            @ApiParam(name = "authenticationRequest", type = "body", value = "To authenticated, you must enter username and password", required = true)
            @RequestBody AuthenticationRequest authenticationRequest,
                                                 HttpServletResponse response,
                                                 HttpServletRequest request);

    @PostMapping(value = {"/google", "/google/{role}"})
    ResponseEntity<?> googleAuthentication(@RequestBody GoogleAuthenticationRequest authenticationRequest,
                                                  @PathVariable(value = "role", required = false) String role,
                                                  HttpServletResponse response,
                                                  HttpServletRequest request);

    @PostMapping(value = "/email")
    ResponseEntity<?> emailRecognition(@RequestBody GoogleAuthenticationRequest authenticationRequest,
                                       HttpServletResponse response,
                                       HttpServletRequest request);
}


