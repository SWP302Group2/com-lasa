package com.lasa.business.controllers.implv1;

import com.lasa.business.controllers.EmailSenderOperations;
import com.lasa.business.services.EmailSenderService;
import com.lasa.business.services.implv1.EmailSenderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/v1/send-mail")
public class EmailSenderController implements EmailSenderOperations {
    private final EmailSenderService emailSenderService;

    @Autowired
    public EmailSenderController(@Qualifier("EmailSenderServiceImpl") EmailSenderService emailSenderService){
        this.emailSenderService = emailSenderService;
    }

    @Override
    public void triggerMail() throws MessagingException {
        emailSenderService.sendEmailWithAttachment("tranvancaotung1@gmail.com",
                "This is email body",
                "This is email subject",
                "C:\\Users\\Admin\\OneDrive - Unicorn\\Desktop\\1.png");
    }
}
