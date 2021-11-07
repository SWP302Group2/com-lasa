/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.services.implv1.FavoriteLecturerServiceImpl;
import com.lasa.data.model.entity.Lecturer;
import com.lasa.data.model.entity.Student;
import com.lasa.data.model.request.FavoriteLecturerRequestModel;
import com.lasa.data.model.view.FavoriteLecturerViewModel;
import com.lasa.data.repo.repository.FavoriteLecturerRepository;
import com.lasa.data.validator.model.ValidList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 *
 * @author ASUS
 */
@SpringBootTest
@AutoConfigureMockMvc
public class FavoriteLecturerControllerTest {
    
   
   @Mock
   FavoriteLecturerRepository favoriteLecturerRepository;
    
   @Mock
   FavoriteLecturerServiceImpl favoriteLecturerService;

   @InjectMocks
   FavoriteLecturerController favoriteLecturerController;
    
    public FavoriteLecturerControllerTest() {
    }

    @Test
    public void testFindAll() throws Exception {
    }

    @Test
    public void testCreateFavoriteLecturer() throws Exception {                    
        Lecturer lecturer = new Lecturer();
        lecturer.setId(3);
        Student student = new Student();
        student.setId(1);
        
        FavoriteLecturerRequestModel model = new FavoriteLecturerRequestModel(3, 1);
        ValidList<FavoriteLecturerRequestModel> favorite = new ValidList<>();    
        favorite.add(model);
        
        FavoriteLecturerViewModel expectedModel = new FavoriteLecturerViewModel(student, lecturer);
        List<FavoriteLecturerViewModel> expected = new ArrayList<>();
        expected.add(expectedModel);
        
        when(favoriteLecturerService.addFavoriteLecturers(favorite)).thenReturn(expected);
        ResponseEntity <List<FavoriteLecturerViewModel>> result = favoriteLecturerController.createFavoriteLecturer(favorite);
        Assertions.assertEquals(result.getBody(),expected);
    }
    
    
}
