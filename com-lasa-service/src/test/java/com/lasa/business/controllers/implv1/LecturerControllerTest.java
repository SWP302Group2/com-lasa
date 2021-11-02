/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lasa.business.services.implv1.LecturerServiceImpl;
import com.lasa.data.model.entity.Lecturer;
import com.lasa.data.model.view.LecturerViewModel;
import com.lasa.data.repo.repository.LecturerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
   
   private final static ObjectMapper objectMapper = new ObjectMapper();
   
   private final int TEST_ID = 44;
    
    public LecturerControllerTest() {
    }

    @Test
    public void testFindAll() throws Exception {
    }

    @Test
    public void testcase_01_FindByLecturerId() throws Exception {
        int id = 1;
        String email = "khiemtd@fe.edu.vn";
        LecturerViewModel expected = new LecturerViewModel();
        expected.setId(id);
        expected.setEmail(email);
        when(lecturerService.findLecturerById(id)).thenReturn(expected);

        ResponseEntity<LecturerViewModel> result = lecturerController.findById(id);
        Assertions.assertEquals(result.getBody(),expected);
    }

 /*   @Test
    public void testcase_02_CreateLecturer() throws Exception {
        
        //Initialized data
        Lecturer expected = new Lecturer();
        expected.setId(TEST_ID);
        expected.setEmail("example000@gmail.com");
        expected.setMeetingUrl("https://meet.google.com/ryu-stnz-rww");
        expected.setStatus(1);
        expected.setName("example");
          
        //Invoke createLecturer Api
        this.mocMvc.perform(
                post("/api/v1/lecturers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expected))
        ).andExpect(status().isOk())
                .andReturn();
        
        //Check result
        Lecturer outputData = lecturerRepository.findById(TEST_ID).get();
        assertEquals(expected.getEmail(), outputData.getEmail());
    }

    @Test
    public void testcase_03_UpdateLecturer() throws Exception {
        
        //Initialized data
        Lecturer expected = new Lecturer();
        expected.setId(TEST_ID);
        expected.setEmail("example111@gmail.com");
        expected.setMeetingUrl("https://meet.google.com/ryu-stnz-rww");
        expected.setStatus(1);
        expected.setName("example");
        
        //Invoke createLecturer Api
        this.mocMvc.perform(
                put("/api/v1/lecturers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expected))
        ).andExpect(status().isOk())
                .andReturn();
        
        //Check result
        Lecturer outputData = lecturerRepository.findById(TEST_ID).get();
            assertEquals(expected.getEmail(), outputData.getEmail());
    }

    @Test
    public void testDeleteLecturers() {
    }*/
    
}