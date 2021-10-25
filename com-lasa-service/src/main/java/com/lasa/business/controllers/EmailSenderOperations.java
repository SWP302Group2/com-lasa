package com.lasa.business.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;

@RequestMapping("/default")
public interface EmailSenderOperations {

    @PostMapping
    public void triggerMail() throws MessagingException;
}
