package com.lasa.business.services;

import com.lasa.data.model.entity.BookingRequest;
import com.lasa.data.model.entity.Slot;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public interface EmailSenderService {
    void sendEmailWithAttachment(String toEmail,
                                        String body,
                                        String subject
                                        ) throws MessagingException;

    void sendEmailAfterBookingAccepted(Slot slot, BookingRequest bookingRequest) throws MessagingException;

    void sendEmailNotifyBeforeMeeting();
}
