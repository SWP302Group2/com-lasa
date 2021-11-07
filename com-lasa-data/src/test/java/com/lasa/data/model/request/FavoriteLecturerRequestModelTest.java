/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.model.request;

import com.lasa.data.model.entity.FavoriteLecturer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author ASUS
 */
@SpringBootTest
public class FavoriteLecturerRequestModelTest {
    
   @Test
    public void toEntityTestGivenRightArgumentReturnWells() {
        FavoriteLecturerRequestModel model = new FavoriteLecturerRequestModel();
        Integer lecturerId = 1;
        Integer studentId = 1;

        model.setLecturerId(lecturerId);
        model.setStudentId(studentId);    
        FavoriteLecturer favoriteLecturer = model.toEntity();

        Assertions.assertEquals(favoriteLecturer.getLecturer().getId() ,lecturerId);
        Assertions.assertEquals(favoriteLecturer.getStudent().getId(), studentId);
    }
    
}
