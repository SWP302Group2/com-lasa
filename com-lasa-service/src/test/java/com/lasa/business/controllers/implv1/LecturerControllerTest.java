/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lasa.business.services.implv1.LecturerServiceImpl;
import com.lasa.data.entity.Lecturer;
import com.lasa.data.repo.repository.LecturerRepository;
import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
import javax.persistence.EntityManager;
//import javax.persistence.PreRemove;
import javax.persistence.criteria.CriteriaBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.mockito.Mockito;
//import static org.mockito.Mockito.times;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.ResultMatcher;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
   
   @Mock
   EntityManager entityManager;
   
   @Mock
   CriteriaBuilder criteriaBuilder;
    
   @InjectMocks
   LecturerServiceImpl lecturerService;
   
   private final static ObjectMapper objectMapper = new ObjectMapper();
   
   private final int TEST_ID = 101;
   

    @Test
    public void testFindAll() throws Exception {
    }

    @Test
    @Rollback(value = false)
    public void testcase_01_FindByLecturerId() throws Exception {
        /*
        //Initialized data
        int ID = 2;
        String expected = "linhnh@fe.edu.vn";
        RequestBuilder request = get("/api/v1/lecturers/"+ID);
        
        //Invoke FindByLecturerId Api
        MvcResult result = this.mocMvc.perform(request).andReturn();   
        
        //Check result
        String Jsonstring = result.getResponse().getContentAsString(); 
        Lecturer outputData = objectMapper.readValue(Jsonstring, Lecturer.class);        
        assertEquals(expected, outputData.getEmail());
        */
    }

    @Test
    @Rollback(value = false)
    public void testcase_02_CreateLecturer() throws Exception {
        
        /*
        objectMapper.findAndRegisterModules();
        
        //Initialized data
        Lecturer expected = new Lecturer();
        expected.setId(TEST_ID);
        expected.setEmail("example000@gmail.com");
        expected.setMeetingUrl("https://meet.google.com/ryu-stnz-rww");
        expected.setStatus(1);
        expected.setName("example");
        expected.setPhone("0123456789");
        expected.setGender(Boolean.TRUE);
        expected.setBirthday(LocalDate.now());
        expected.setAddress("Ho Chi Minh City");
        expected.setAvatarUrl("Avartar");
          
        //Invoke createLecturer Api
        this.mocMvc.perform(
                post("/api/v1/lecturers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expected))
        ).andExpect(status().isOk())
                .andReturn();
        
        //Check result
        Lecturer outputData = lecturerRepository.findById(TEST_ID).get();
            assertEquals(expected.getName(), outputData.getName());
            
            assertEquals(expected.getEmail(), outputData.getEmail());
            assertEquals(expected.getPhone(), outputData.getPhone());
            assertEquals(expected.getMeetingUrl(), outputData.getMeetingUrl());
            assertEquals(expected.getStatus(), outputData.getStatus());
            assertEquals(expected.getGender(), outputData.getGender());
            assertEquals(expected.getBirthday(), outputData.getBirthday());
            assertEquals(expected.getAddress(), outputData.getAddress());
            assertEquals(expected.getAvatarUrl(), outputData.getAvatarUrl());
            
        */
    }

    
    @Test
    @Rollback(value = false)
    public void testcase_03_UpdateLecturer() throws Exception 
    {
        /*
        //Initialized data
        Lecturer expected = new Lecturer();
        expected.setId(TEST_ID);
        expected.setEmail("example111@gmail.com");
        expected.setMeetingUrl("https://meet.google.com/ryu-stnz-rww");
        expected.setStatus(1);
        expected.setName("example111");
        expected.setPhone("0123456789");
        expected.setGender(Boolean.TRUE);
        expected.setBirthday(LocalDate.now());
        expected.setAddress("Ho Chi Minh City");
        expected.setAvatarUrl("Avartar");
        
        Lecturer outputData = new Lecturer();
        
        //Mockito.when(lecturerService.updateLecturer(expected)).a
        
        //Invoke createLecturer Api
        this.mocMvc.perform(
                put("/api/v1/lecturers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expected))
        ).andExpect(status().isOk())
                .andReturn();
        
        //Check result
        outputData = lecturerRepository.findById(TEST_ID).get();
            assertEquals(expected.getName(), outputData.getName());
            
            assertEquals(expected.getEmail(), outputData.getEmail());
            assertEquals(expected.getPhone(), outputData.getPhone());
            assertEquals(expected.getMeetingUrl(), outputData.getMeetingUrl());
            assertEquals(expected.getStatus(), outputData.getStatus());
            assertEquals(expected.getGender(), outputData.getGender());
            assertEquals(expected.getBirthday(), outputData.getBirthday());
            assertEquals(expected.getAddress(), outputData.getAddress());
            assertEquals(expected.getAvatarUrl(), outputData.getAvatarUrl());
            
      */
    }

    /*
    @Test
    @Rollback(value = false)
    public void testcase_04_DeleteLecturers() throws Exception {
       
        List<Integer> ids = new ArrayList<>();
        ids.add(TEST_ID);
        

       MvcResult result = this.mocMvc.perform(
               delete("/api/v1/lecturers/")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsBytes(ids))
                        ).andExpect(status().isOk()).andReturn();
       
        if(result.getResponse().getStatus() == 200)
        {
            LecturerServiceImplV1 tmp = new LecturerServiceImplV1(lecturerRepository
            ,entityManager);
            
            tmp.deleteLecturers(ids);
            
        }
       
        boolean isExist = lecturerRepository.existsById(TEST_ID);
        
        assertEquals(false,isExist);
        
    }
    */
}
