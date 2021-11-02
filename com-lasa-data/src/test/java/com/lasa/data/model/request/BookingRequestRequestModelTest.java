/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.model.request;

import com.lasa.data.model.entity.BookingRequest;
import com.lasa.data.model.entity.Question;
import com.lasa.data.validator.group.PostValidator;
import com.lasa.data.validator.group.PutValidator;
import org.hibernate.validator.HibernateValidator;
import org.junit.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author ASUS
 */
@SpringBootTest
public class BookingRequestRequestModelTest {
    
    @Test
    public void toEntityTestGivenRightArgumentReturnWells() {
        BookingRequestRequestModel model = new BookingRequestRequestModel();
        Integer id = 1;
        Integer studentId = 1;
        Integer status = 1;
        Integer topicId = 261;
        Integer slotId = 1;
        String title = "I need to help";

        model.setId(id);
        model.setStudentId(studentId);
        model.setStatus(status);
        model.setTopicId(topicId);
        model.setSlotId(slotId);
        model.setTitle(title);       

        BookingRequest bookingRequest = model.toEntity();

        assertEquals(bookingRequest.getId(), id);
        assertEquals(bookingRequest.getStudentId(), studentId);
        assertEquals(bookingRequest.getStatus(), status);
        assertEquals(bookingRequest.getTopicId(), topicId);
        assertEquals(bookingRequest.getSlotId(), slotId);
        assertEquals(bookingRequest.getTitle(), title);
    }



//    @Autowired
//    private  ValidatorFactory validatorFactory;
//    @Autowired
//    private  Validator validator;
//
//    @BeforeClass
//    public static void createValidator() {
//
//    }
//
//
//    public static void close() {
//
//    }
//
//    @Test
//    public void test(){
//        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
//                .configure()
//                .buildValidatorFactory();
//        Validator validator = validatorFactory.getValidator();
//
////        BookingRequestRequestModel bookingRequestRequestModel = new BookingRequestRequestModel();
////        bookingRequestRequestModel.setId(null);
////        bookingRequestRequestModel.setRating(1);
////        bookingRequestRequestModel.setQuestions(null);
////        bookingRequestRequestModel.setCreateTime(null);
////        bookingRequestRequestModel.setTitle("help");
////        bookingRequestRequestModel.setSlotId(1);
////        bookingRequestRequestModel.setTopicId(2);
////        bookingRequestRequestModel.setStatus(1);
////        bookingRequestRequestModel.setStudentId(2);
//        Set<ConstraintViolation<BookingRequestRequestModel>> violations = validator.validateValue(
//                BookingRequestRequestModel.class, "studentId",null);
//        Assert.assertEquals( "STUDENT_ID_IS_NULL", violations.iterator().next().getMessage() );
//        validatorFactory.close();
//
//    }

    private static Validator validator;

    @BeforeClass
    public static void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void test2(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        BookingRequestRequestModel build = buildBookingRequestModel();
        build.setTitle("");

        Set<ConstraintViolation<BookingRequestRequestModel>> violations = validator.validate(build, PostValidator.class);
        violations.forEach(action -> {
            Assert.assertEquals( "ABC", action.getMessage() );

        });
    }

    public BookingRequestRequestModel buildBookingRequestModel(){
        BookingRequestRequestModel bookingRequestRequestModel = new BookingRequestRequestModel();

        QuestionRequestModel questionRequestModel = new QuestionRequestModel();
        questionRequestModel.setId(700);
        questionRequestModel.setBookingId(1);
        questionRequestModel.setContent("abc");
        List<QuestionRequestModel> list = new ArrayList<>();
        list.add(questionRequestModel);

        LocalDateTime now = LocalDateTime.now();
        bookingRequestRequestModel.setId(1);
        bookingRequestRequestModel.setRating(1);
        bookingRequestRequestModel.setQuestions(list);
        bookingRequestRequestModel.setCreateTime(now);
        bookingRequestRequestModel.setTitle("help");
        bookingRequestRequestModel.setSlotId(1);
        bookingRequestRequestModel.setTopicId(86);
        bookingRequestRequestModel.setStatus(1);
        bookingRequestRequestModel.setStudentId(1);
        return  bookingRequestRequestModel;
    }
    
}
