package com.lasa.business.services.implv1;

import com.lasa.business.config.utils.BookingRequestStatus;
import com.lasa.business.config.utils.SlotStatus;
import com.lasa.business.services.BookingRequestService;
import com.lasa.business.services.EmailSenderService;
import com.lasa.business.services.SlotService;
import com.lasa.data.model.entity.BookingRequest;
import com.lasa.data.model.entity.Lecturer;
import com.lasa.data.model.entity.Slot;
import com.lasa.data.model.entity.Student;
import com.lasa.data.model.request.BookingRequestRequestModel;
import com.lasa.data.model.request.SlotRequestModel;
import com.lasa.data.model.utils.criteria.BookingRequestSearchCriteria;
import com.lasa.data.model.utils.criteria.SlotSearchCriteria;
import com.lasa.data.model.view.BookingRequestViewModel;
import com.lasa.data.model.view.SlotViewModel;
import com.lasa.data.repo.repository.LecturerRepository;
import com.lasa.data.repo.repository.StudentRepository;
import com.lasa.security.utils.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Qualifier("EmailSenderServiceImplV1")
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender mailSender;
    private final Environment environment;
    private final StudentRepository studentRepository;
    private final LecturerRepository lecturerRepository;
    private SlotService slotService;
    private BookingRequestService bookingRequestService;

    @Autowired
    public EmailSenderServiceImpl(JavaMailSender mailSender,
                                  Environment environment,
                                  StudentRepository studentRepository,
                                  LecturerRepository lecturerRepository) {
        this.mailSender = mailSender;
        this.environment = environment;
        this.studentRepository = studentRepository;
        this.lecturerRepository = lecturerRepository;
    }

    @Autowired
    public void setSlotService(SlotService slotService) {
        this.slotService = slotService;
    }

    @Autowired
    public void setBookingRequestService(BookingRequestService bookingRequestService) {
        this.bookingRequestService = bookingRequestService;
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
                        "\n You have successfully made an appointment with your mentor on " + dateStartFormat + "." +
                        "\n Time for meeting " + timeStartFormat + "-" + timeEndFormat + "." +
                        "\n The host of meeting is: "+ lecturer.getName() + "." +
                        "\n Hear is link to google meet: " + lecturer.getMeetingUrl() + "." +
                        "\n" +
                        "\n" +
                        "\n Best regards, " +
                        "\n Lasa customer service team.",
                "BookingRequest Accepted for " + bookingRequest.getTitle());

    }

    @Override
    @Scheduled(fixedDelay = 100000L)
    public void sendEmailNotifyBeforeMeeting() {
        SlotSearchCriteria slotSearchCriteria = SlotSearchCriteria.builder()
                .timeStart(LocalDateTime.now())
                .timeEnd(LocalDateTime.now().plusHours(6))
                .getLecturer(true)
                .getTopic(false)
                .status(SlotStatus.ACCEPTED.getCode())
                .build();

        List<SlotViewModel> slots = slotService.findWithArguments(slotSearchCriteria);

        if(!slots.isEmpty()) {
            BookingRequestSearchCriteria bookingRequestSearchCriteria = BookingRequestSearchCriteria.builder()
                    .status(BookingRequestStatus.ACCEPTED.getCode())
                    .slotId(slots.stream()
                            .map(t -> t.getId())
                            .collect(Collectors.toList()))
                    .getStudent(true)
                    .build();

            List<BookingRequestViewModel> bookingRequests = bookingRequestService.findAll(bookingRequestSearchCriteria);

            bookingRequests.stream().forEach(t -> {
                slots.stream().forEach(x -> {
                    if (x.getId().equals(t.getSlotId())) {
                        LocalDateTime timeStart = x.getTimeStart();
                        LocalDateTime timeEnd = x.getTimeEnd();

                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String dateStartFormat = timeStart.format(dateFormatter);

                        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                        String timeStartFormat = timeStart.format(timeFormatter);
                        String timeEndFormat = timeEnd.format(timeFormatter);

                        SlotRequestModel slotRequestModel = new SlotRequestModel();
                        slotRequestModel.setId(x.getId());
                        slotRequestModel.setStatus(SlotStatus.NOTIFIED.getCode());
                        slotService.updateSlots(slotRequestModel);

                        BookingRequestRequestModel bookingRequestRequestModel = new BookingRequestRequestModel();
                        bookingRequestRequestModel.setId(t.getId());
                        bookingRequestRequestModel.setStatus(BookingRequestStatus.NOTIFIED.getCode());
                        bookingRequestService.updateBookingRequest(bookingRequestRequestModel);

                        try {
                            sendEmailWithAttachment(
                                    t.getStudent().getEmail(),
                                    "Dear Mr/Ms " + t.getStudent().getName() + ", " + t.getStudent().getMssv() +
                                            "\n You have a meeting on " + dateStartFormat + "." +
                                            "\n Time for meeting " + timeStartFormat + "-" + timeEndFormat + "." +
                                            "\n The host of meeting is: "+ x.getLecturer().getName() + "." +
                                            "\n Hear is link to google meet: " + x.getLecturer().getMeetingUrl() + "." +
                                            "\n" +
                                            "\n" +
                                            "\n Best regards, " +
                                            "\n Lasa customer service team.",
                                    "Notification Email for " + t.getTitle());

                            sendEmailWithAttachment(
                                    x.getLecturer().getEmail(),
                                    "Dear Mr/Ms " + x.getLecturer().getEmail()  +
                                            "\n You have a meeting on " + dateStartFormat + "." +
                                            "\n Time for meeting " + timeStartFormat + "-" + timeEndFormat + "." +
                                            "\n Hear is link to google meet: " + x.getLecturer().getMeetingUrl() + "." +
                                            "\n" +
                                            "\n" +
                                            "\n Best regards, " +
                                            "\n Lasa customer service team.",
                                    "Notification Email for your Slot " )  ;
                        } catch (MessagingException e) {
                            throw new ExceptionUtils.EmailSenderException("EMAIL_SENDER_EMAIL");
                        }
                    }
                });
            });
        }

    }

}
