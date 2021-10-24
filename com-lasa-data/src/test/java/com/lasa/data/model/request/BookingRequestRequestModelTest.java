/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.model.request;

import com.lasa.data.model.entity.BookingRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

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

        Assertions.assertEquals(bookingRequest.getId(), id);
        Assertions.assertEquals(bookingRequest.getStudentId(), studentId);
        Assertions.assertEquals(bookingRequest.getStatus(), status);
        Assertions.assertEquals(bookingRequest.getTopicId(), topicId);
        Assertions.assertEquals(bookingRequest.getSlotId(), slotId);
        Assertions.assertEquals(bookingRequest.getTitle(), title);
    }
    
}
