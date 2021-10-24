package com.lasa.business.services;

import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public interface EmailSenderService {
    public void sendEmailWithAttachment(String toEmail,
                                        String body,
                                        String subject,
                                        String attachment) throws MessagingException;
}
