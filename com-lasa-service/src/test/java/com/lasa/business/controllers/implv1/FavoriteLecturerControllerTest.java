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
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

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
/*
    @Test
    public void testCreateFavoriteLecturer() throws Exception {        
        List<FavoriteLecturerRequestModel> favorite = new ArrayList<>();
       
        favorite.setStudentId(1);
             
        Lecturer lecturer = new Lecturer();
        lecturer.setId(3);
        Student student = new Student();
        student.setId(1);
        FavoriteLecturerViewModel expected = new FavoriteLecturerViewModel();
        expected.setLecturer(lecturer);
        expected.setStudent(student);
        
        when(favoriteLecturerService.addFavoriteLecturers(favorite)).thenReturn(expected);
        ResponseEntity<BookingRequestViewModel> result = bookingController.createBookingRequest(booking);
        Assertions.assertEquals(result.getBody(),expected);
    }*/
    
    
}
