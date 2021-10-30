/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repo.repository;

import com.lasa.data.model.entity.FavoriteLecturer;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 *
 * @author ASUS
 */
@DataJpaTest
public class FavoriteLecturerRepositoryTest {
    
    @Autowired
    FavoriteLecturerRepository favoriteRequestRepository;
    
    public FavoriteLecturerRepositoryTest() {
    }
    
    @Test
    public void testFindAllLecturerAndStudentInFavoriteLecturer() {
        /*String expectedEmail = "thunasa160090@fpt.edu.vn";
         String expectedEmailLecturer = "khiemtd@fe.edu.vn";
        List<FavoriteLecturer> favorite = favoriteRequestRepository.findAllLecturerAndStudentInFavoriteLecturer();
        Assertions.assertEquals(favorite.get(0).getStudent().getEmail(), expectedEmail);
        Assertions.assertEquals(favorite.get(0).getLecturer().getEmail(), expectedEmailLecturer);
        */
        
    }

    
}
