/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lasa.business.services.implv1.LecturerServiceImpl;
import com.lasa.data.model.entity.Lecturer;
import com.lasa.data.repo.repository.LecturerRepository;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author ASUS
 */
@TestMethodOrder(Alphanumeric.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LecturerControllerTest {
    
   @Autowired
   private MockMvc mocMvc;
   
   @Autowired
   LecturerRepository lecturerRepository;
    
   @InjectMocks
   LecturerServiceImpl lecturerService;
   
   private final static ObjectMapper objectMapper = new ObjectMapper();
   
   private final int TEST_ID = 44;
    
    public LecturerControllerTest() {
    }

    @Test
    public void testFindAll() throws Exception {
    }

    @Test
    public void testcase_01_FindByLecturerId() throws Exception {
        int ID = 1;
        String expected = "khiemtd@fe.edu.vn";
        RequestBuilder request = get("/api/v1/lecturers/"+ID);
        MvcResult result = this.mocMvc.perform(request).andReturn();   

        String Jsonstring = result.getResponse().getContentAsString(); 
        Lecturer outputData = objectMapper.readValue(Jsonstring, Lecturer.class);        
        assertEquals(expected, outputData.getEmail());
        
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