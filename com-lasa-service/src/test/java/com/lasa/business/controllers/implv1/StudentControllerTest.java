/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.services.implv1.StudentServiceImpl;
import com.lasa.data.model.request.StudentRequestModel;
import com.lasa.data.model.view.StudentViewModel;
import com.lasa.data.repo.repository.StudentRepository;
import com.lasa.security.utils.exception.ExceptionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author ASUS
 */
@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {
    @Mock
    StudentRepository studentRepository;
    
    @Mock
    StudentServiceImpl studentService;

    @InjectMocks
   StudentController studentController;
    public StudentControllerTest() {
    }


    @Test
    public void testFindByStudentId() {
         int id = 1;
        StudentViewModel expected = new StudentViewModel();
        expected.setId(id);
        expected.setName("Khiem");
        
        when(studentService.findByStudentId(1)).thenReturn(expected);
        ResponseEntity<StudentViewModel> result = studentController.findByStudentId(1);
        Assertions.assertEquals(result.getBody(),expected);
    }
    
    @Test
    public void testCreateStudent() {
         int id = 1;
         
        StudentRequestModel student = new StudentRequestModel();
        student.setId(id);
        student.setName("Khiem");
        
        StudentViewModel expected = new StudentViewModel();
        expected.setId(id);
        expected.setName("Khiem");
        
        when(studentService.createStudent(student)).thenReturn(expected);
        ResponseEntity<StudentViewModel> result = studentController.createStudent(student);
        Assertions.assertEquals(result.getBody(),expected);
    }
    
    @Test
    public void testUpdateStudent() throws ExceptionUtils.UpdateException {
         int id = 1;
         
        StudentRequestModel student = new StudentRequestModel();
        student.setId(id);
        student.setName("Khiem");
        
        StudentViewModel expected = new StudentViewModel();
        expected.setId(id);
        expected.setName("Hoa");
        
        when(studentService.updateStudent(student)).thenReturn(expected);
        ResponseEntity<StudentViewModel> result = studentController.updateStudent(student);
        Assertions.assertEquals(result.getBody(),expected);
    }
    

}
