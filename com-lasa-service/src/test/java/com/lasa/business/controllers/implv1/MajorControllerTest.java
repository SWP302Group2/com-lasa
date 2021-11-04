/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.services.implv1.MajorServiceImpl;
import com.lasa.data.model.entity.Major;
import com.lasa.data.model.request.MajorRequestModel;
import com.lasa.data.model.view.MajorViewModel;
import com.lasa.data.repo.repository.MajorRepository;
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
public class MajorControllerTest {
    
    @Mock
    MajorRepository majorRepository;
    
   @Mock
   MajorServiceImpl majorService;

   @InjectMocks
   MajorController majorController;
    public MajorControllerTest() {
    }

    @Test
    public void testFindMajorById() throws Exception {
        String id = "SE151295";
        MajorViewModel expected = new MajorViewModel();
        expected.setId(id);
        when(majorService.findById(id)).thenReturn(expected);
        ResponseEntity<MajorViewModel> result = majorController.findById(id);
        Assertions.assertEquals(result.getBody(),expected);
    }
    
    @Test
    public void testCreateMajor() throws Exception {     
        
        MajorRequestModel model = new MajorRequestModel();
        List<MajorRequestModel> majorModel = new ArrayList<>();
        model.setId("SE124578");
        
        Major major = new Major();
        major.setId("SE124578");
        MajorViewModel expectedModel = new MajorViewModel(major);
        List<MajorViewModel> expected = new ArrayList<>();
        expected.add(expectedModel);
        
        when(majorService.createMajors(majorModel)).thenReturn(expected);
        ResponseEntity<List<MajorViewModel>> result = majorController.createMajors(majorModel);
        Assertions.assertEquals(result.getBody().get(0).getId(),"SE124578");
    }
    
    @Test
    public void testUpdateMajor() throws Exception {     
        Major major = new Major();
        major.setId("SE11111");
        major.setName("SS");
        
        MajorRequestModel model = new MajorRequestModel();
        List<MajorRequestModel> majorModel = new ArrayList<>();
        model.setId("SE111111");
        model.setName("SE");

        MajorViewModel expectedModel = new MajorViewModel(major);
        List<MajorViewModel> expected = new ArrayList<>();
        expected.add(expectedModel);
        
        when(majorService.updateMajors(majorModel)).thenReturn(expected);
        ResponseEntity<?> result = majorController.updateMajors(majorModel);
        Assertions.assertEquals(result.getBody(),expected);
    }
    
}
