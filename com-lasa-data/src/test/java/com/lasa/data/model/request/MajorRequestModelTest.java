/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.model.request;

import com.lasa.data.model.entity.Major;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author ASUS
 */
@SpringBootTest
public class MajorRequestModelTest {
    
    @Test
    public void toEntityTestGivenRightArgumentReturnWells() {
        MajorRequestModel model = new MajorRequestModel();
        String id = "3";
        String name = "Software Engineering";
        String description = "SE";
        

        model.setId(id);
        model.setName(name);
        model.setDescription(description);
      
        Major major = model.toEntity();

        Assertions.assertEquals(major.getId(), id);
        Assertions.assertEquals(major.getName(), name);
        Assertions.assertEquals(major.getDescription(), description);
        
    }
}
