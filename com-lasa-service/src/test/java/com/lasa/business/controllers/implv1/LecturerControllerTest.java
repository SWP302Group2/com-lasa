/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.services.implv1.LecturerServiceImpl;
import com.lasa.data.model.request.LecturerRequestModel;
import com.lasa.data.model.view.LecturerViewModel;
import com.lasa.data.repo.repository.LecturerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
/**
 *
 * @author ASUS
 */
@SpringBootTest
@AutoConfigureMockMvc
public class LecturerControllerTest {
    
   @Autowired
   private MockMvc mocMvc;
   
   @Mock
   LecturerRepository lecturerRepository;
    
   @Mock
   LecturerServiceImpl lecturerService;

   @InjectMocks
   LecturerController lecturerController;
    
    public LecturerControllerTest() {
    }

    @Test
    public void testFindAll() throws Exception {
    }

    @Test
    public void testFindByLecturerId() throws Exception {
        int id = 1;
        String email = "khiemtd@fe.edu.vn";
        LecturerViewModel expected = new LecturerViewModel();
        expected.setId(id);
        expected.setEmail(email);
        when(lecturerService.findLecturerById(id)).thenReturn(expected);
        ResponseEntity<LecturerViewModel> result = lecturerController.findById(id);
        Assertions.assertEquals(result.getBody(),expected);
    }
    @Test
    public void testCreateLecturer() throws Exception {        
        LecturerRequestModel lecturer = new LecturerRequestModel();
        lecturer.setId(1);
        lecturer.setMeetingUrl("https://meet.google.com/ryu-stnz-rww");
        lecturer.setStatus(1);
        lecturer.setName("Khiem");
        
        LecturerViewModel expected = new LecturerViewModel();
        expected.setId(1);
        expected.setEmail("khiemtd@fe.edu.vn");
        expected.setMeetingUrl("https://meet.google.com/ryu-stnz-rww");
        expected.setStatus(1);
        expected.setName("Khiem");
        when(lecturerService.createLecturer(lecturer)).thenReturn(expected);
        ResponseEntity<LecturerViewModel> result = lecturerController.createLecturer(lecturer);
        Assertions.assertEquals(result.getBody(),expected);
    }
    
        @Test
        public void testUpdateLecturer() throws Exception {
        LecturerRequestModel lecturer = new LecturerRequestModel();
        lecturer.setId(1);
        lecturer.setMeetingUrl("https://meet.google.com/ryu-stnz-rww");
        lecturer.setStatus(1);
        lecturer.setName("Khiem");
        
        LecturerViewModel expected = new LecturerViewModel();
        expected.setId(11);
        expected.setMeetingUrl("https://meet.google.com/ryu-stnz-rww");
        expected.setStatus(1);
        expected.setName("Hoa");
        when(lecturerService.updateLecturer(lecturer)).thenReturn(expected);
        ResponseEntity<LecturerViewModel> result = lecturerController.updateLecturer(lecturer);
        Assertions.assertEquals(result.getBody(),expected);
    }
    
    
}