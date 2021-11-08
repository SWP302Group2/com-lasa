package com.lasa.business.services;

import com.lasa.business.services.implv1.StudentServiceImpl;
import com.lasa.data.model.entity.Student;
import com.lasa.data.model.entity.key.SlotTopicDetailKey;
import com.lasa.data.model.request.StudentRequestModel;
import com.lasa.data.model.view.StudentViewModel;
import com.lasa.data.repo.repository.FavoriteLecturerRepository;
import com.lasa.data.repo.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private FavoriteLecturerRepository favoriteLecturerRepository;
    @InjectMocks
    private StudentServiceImpl studentService;

    private StudentRequestModel studentRequestModel;
    private Student student;

    @BeforeEach
    void setUp() {
        studentService = new StudentServiceImpl(studentRepository, favoriteLecturerRepository);

        studentRequestModel = new StudentRequestModel();
        studentRequestModel.setId(1);
        studentRequestModel.setMssv("SE151344");
        student = new Student();
        student = studentRequestModel.toEntity();


    }

    @Test
    void findWithArgument() {
    }

    @Test
    void testFindWithArgument() {
    }

    @Test
    void shouldReturnRightMssvWhenFindByStudentId() {
        Optional<Student> studentOptional = Optional.of(student);
        when(studentRepository.findById(1))
                .thenReturn(studentOptional);
        StudentViewModel result = studentService.findByStudentId(1);
        assertEquals("SE151344", result.getMssv());
    }

    @Test
    void createStudent() {
        // Null
    }

    @Test
    void updateStudent() {
//        when(studentRepository.findById(studentRequestModel.getId()))
//                .thenReturn()
    }

    @Test
    void deleteStudents() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);

        studentService.deleteStudents(ids);
        verify(studentRepository).findAllById(ids);
    }
}