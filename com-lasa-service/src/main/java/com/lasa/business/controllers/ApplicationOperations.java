package com.lasa.business.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@RequestMapping("/default")
@ApiIgnore
public interface ApplicationOperations {
    @GetMapping(value = "/information")
    ResponseEntity<?> getUserInformation();
}
