/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.model.request;

import com.lasa.data.model.entity.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author ASUS
 */
@SpringBootTest
public class QuestionRequestModelTest {
    
    @Test
    public void toEntityTestGivenRightArgumentReturnWells() {
        QuestionRequestModel model = new QuestionRequestModel();
        Integer id = 1;
        Integer bookingId = 1;
        String content = "question?";     

        model.setId(id);
        model.setBookingId(bookingId);
        model.setContent(content);
      
        Question question = model.toEntity();

        Assertions.assertEquals(question.getId(), id);
        Assertions.assertEquals(question.getBookingRequest().getId(), bookingId);
        Assertions.assertEquals(question.getContent(), content);
        
    }

    /*@Test
    public void testDataBind() {
        QuestionRequestModel model = new QuestionRequestModel();
        Integer id = 1;
        Integer bookingId = null;
        String content = "question?";

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<QuestionRequestModel>> violations = validator.validate(model);
        for (ConstraintViolation<QuestionRequestModel> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }*/
}
