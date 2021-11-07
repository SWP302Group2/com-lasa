/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repo.repository;

import com.lasa.data.model.entity.Lecturer;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 *
 * @author ASUS
 */
@DataJpaTest
public class LecturerRepositoryTest {
    
     @Autowired
    LecturerRepository lecturerRepository;
     
    public LecturerRepositoryTest() {
    }
    /* @Test
    public void testFindByName() {  
         Lecturer lecturer = lecturerRepository
                 .findByName("Trần Duy Khiêm").get();
        String expectedLecturerName = "Trần Duy Khiêm";
        Assertions.assertEquals(lecturer.getName(), expectedLecturerName);
    }*/
    
     @Test
    public void testFindLecturerByEmail() {      
         Lecturer lecturer = lecturerRepository
                 .findById(1).get();     
        String expectedLecturerEmail = "khiemtd@fe.edu.vn";
        Assertions.assertEquals(lecturer.getEmail(), expectedLecturerEmail);
    }
   
}
