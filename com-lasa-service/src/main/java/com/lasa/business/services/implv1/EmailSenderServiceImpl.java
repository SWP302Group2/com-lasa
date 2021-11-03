package com.lasa.business.services.implv1;

import com.lasa.business.services.EmailSenderService;
import com.lasa.data.model.entity.BookingRequest;
import com.lasa.data.model.entity.Lecturer;
import com.lasa.data.model.entity.Slot;
import com.lasa.data.model.entity.Student;
import com.lasa.data.repo.repository.LecturerRepository;
import com.lasa.data.repo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Qualifier("EmailSenderServiceImplV1")
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender mailSender;
    private final Environment environment;
    private final StudentRepository studentRepository;
    private final LecturerRepository lecturerRepository;

    @Autowired
    public EmailSenderServiceImpl(JavaMailSender mailSender, Environment environment, StudentRepository studentRepository, LecturerRepository lecturerRepository) {
        this.mailSender = mailSender;
        this.environment = environment;
        this.studentRepository = studentRepository;
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public void sendEmailWithAttachment(String toEmail, String body, String subject) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

        mimeMessageHelper.setFrom(environment.getProperty("spring.mail.username"));
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);
        mailSender.send(mimeMessage);

    }

    @Override
    @Transactional
    public void sendEmailAfterBookingAccepted(Slot slot, BookingRequest bookingRequest) throws MessagingException {
            Lecturer lecturer = lecturerRepository.findById(slot.getLecturerId()).get();
            Student student = studentRepository.findById(bookingRequest.getStudentId()).get();

        LocalDateTime timeStart = slot.getTimeStart();
        LocalDateTime timeEnd = slot.getTimeEnd();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateStartFormat = timeStart.format(dateFormatter);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeStartFormat = timeStart.format(timeFormatter);
        String timeEndFormat = timeEnd.format(timeFormatter);
        sendEmailWithAttachment(
                student.getEmail(),
                "Dear Mr/Ms " + student.getName() + ", " + student.getMssv() +
                        "\n You have successfully made an appointment with your mentor on " + dateStartFormat +
                        "\n Time for meeting " + timeStartFormat + "-" + timeEndFormat +
                        "\n The host of meeting is: "+ lecturer.getName() +
                        "\n Hear is link to google meet: " + lecturer.getMeetingUrl() +
                        "\n" +
                        "\n" +
                        "\n Best regards, " +
                        "\n Lasa customer service team",
                "BookingRequest Accepted for " + bookingRequest.getTitle())

    }

}
