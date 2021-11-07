package com.lasa.business.controllers;

import com.lasa.security.appuser.MyUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@RequestMapping("/default")
@ApiIgnore
public interface ApplicationOperations {
    @GetMapping(value = "/information")
    ResponseEntity<?> getUserInformation();
}
