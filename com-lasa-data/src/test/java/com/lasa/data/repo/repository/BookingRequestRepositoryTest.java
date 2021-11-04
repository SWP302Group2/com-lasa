/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repo.repository;

import com.lasa.data.model.entity.BookingRequest;
import com.lasa.data.model.entity.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

/**
 *
 * @author MyNhung
 */
@DataJpaTest
public class BookingRequestRepositoryTest {
    
    @Autowired
    BookingRequestRepository bookingRequestRepository;
    
    public BookingRequestRepositoryTest() {
    }
    
    @Test
    public void testFindByIdAndGetQuestions() {
        
        boolean IsChecked = false;
        
        BookingRequest booking = bookingRequestRepository.findByIdAndGetQuestions(1).get();
        List<Question> listQuestion = booking.getQuestions();
       
        String expectedOutput01 = "Where do you see yourself in five years?";
        String expectedOutput02 = "What motivates you to work hard?";
        
        for(Question temp : listQuestion)
        {
            if(temp.getContent().equals(expectedOutput01) || 
                    temp.getContent().equals(expectedOutput02) )
            {
                IsChecked = true;
            }
        }
        
        Assertions.assertEquals(IsChecked, true);
    }

    @Test
    public void testFindBookingRequestByStudentIdAndSlotId() {
        
         BookingRequest booking = bookingRequestRepository
                 .findBookingRequestByStudentIdAndSlotId(5, 2).get();
         
        int expectedBookingId = 5;
        
        Assertions.assertEquals(booking.getId(), expectedBookingId);
        
    }

    
}
