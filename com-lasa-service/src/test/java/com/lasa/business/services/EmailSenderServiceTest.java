package com.lasa.business.services;

import com.lasa.business.services.implv1.EmailSenderServiceImpl;
import com.lasa.business.services.implv1.LecturerServiceImpl;
import com.lasa.business.services.implv1.StudentServiceImpl;
import com.lasa.data.model.entity.BookingRequest;
import com.lasa.data.model.entity.Lecturer;
import com.lasa.data.model.entity.Slot;
import com.lasa.data.model.entity.Student;
import com.lasa.data.repo.repository.FavoriteLecturerRepository;
import com.lasa.data.repo.repository.LecturerRepository;
import com.lasa.data.repo.repository.LecturerTopicDetailRepository;
import com.lasa.data.repo.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.mail.MessagingException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class EmailSenderServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @Mock
    private LecturerRepository lecturerRepository;

    @Mock
    private FavoriteLecturerRepository favoriteLecturerRepository;

    private StudentServiceImpl studentService;
    private LecturerServiceImpl lecturerService;
    private LecturerTopicDetailRepository lecturerTopicDetailRepository;

    @InjectMocks
    private EmailSenderServiceImpl emailSenderService;

    private JavaMailSender mailSender;
    private Environment environment;
    private Slot slot;
    private BookingRequest bookingRequest;

    @BeforeEach
    public void setup(){
        studentService = new StudentServiceImpl(studentRepository, favoriteLecturerRepository);
        lecturerService = new LecturerServiceImpl(lecturerRepository, favoriteLecturerRepository, lecturerTopicDetailRepository);
        emailSenderService = new EmailSenderServiceImpl(mailSender, environment, studentRepository, lecturerRepository);

        slot = new Slot();
        slot = Slot.builder().id(1)
                .lecturerId(1)
                .build();
        bookingRequest = new BookingRequest();
        bookingRequest = BookingRequest.builder()
                .id(1)
                .studentId(1)
                .build();
    }
    @Test
    public void sendEmailAfterBookingAccepted() throws MessagingException {
        Optional<Student> student = Optional.of(Student.builder().id(1).build());
        when(studentRepository.findById(bookingRequest.getStudentId()))
                .thenReturn(student);
        studentService.findByStudentId(1);
        Optional<Lecturer> lecturer = Optional.of(Lecturer.builder().id(1).build());
        when(lecturerRepository.findById(slot.getLecturerId()))
                .thenReturn(lecturer);
        lecturerService.findLecturerById(1);
        assertEquals(1, student.get().getId());
        assertEquals(1, lecturer.get().getId());
    }

}
