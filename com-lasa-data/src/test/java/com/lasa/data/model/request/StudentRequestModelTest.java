/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.model.request;

import com.lasa.data.model.entity.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author ASUS
 */
@SpringBootTest
public class StudentRequestModelTest {
    
    @Test
    public void toEntityTestGivenRightArgumentReturnWells() {
        StudentRequestModel model = new StudentRequestModel();
        Integer id = 1;
        String mssv = "SE62163";
        String name = "Châu";
        String phone = "0915241265";
        Integer status = 1;
        Boolean gender = true;

        model.setId(id);
        model.setMssv(mssv); 
        model.setName(name); 
        model.setPhone(phone);
        model.setStatus(status); 
        model.setGender(gender);
          
        Student student = model.toEntity();

        Assertions.assertEquals(student.getId() ,id);
        Assertions.assertEquals(student.getMssv(), mssv);
        Assertions.assertEquals(student.getName(), name);
        Assertions.assertEquals(student.getPhone() ,phone);
        Assertions.assertEquals(student.getStatus(), status);
        Assertions.assertEquals(student.getGender() ,gender);
        
    }
    
    
}
