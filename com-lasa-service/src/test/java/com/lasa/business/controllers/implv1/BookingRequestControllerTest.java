/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.services.implv1.BookingRequestServiceImpl;
import com.lasa.data.model.request.BookingRequestRequestModel;
import com.lasa.data.model.view.BookingRequestViewModel;
import com.lasa.data.repo.repository.BookingRequestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

/**
 *
 * @author ASUS
 */
@SpringBootTest
@AutoConfigureMockMvc
public class BookingRequestControllerTest {
    
   @Autowired
   private MockMvc mocMvc;
   
   @Mock
   BookingRequestRepository bookingRepository;
    
   @Mock
   BookingRequestServiceImpl bookingService;

   @InjectMocks
   BookingRequestController bookingController;
    
    public BookingRequestControllerTest() {
    }

    @Test
    public void testFindAll() throws Exception {
    }

    @Test
    public void testFindBookingRequestById() throws Exception {
        int id = 1;
        BookingRequestViewModel expected = new BookingRequestViewModel();
        expected.setId(id);
        when(bookingService.findByBookingRequestId(id)).thenReturn(expected);
        ResponseEntity<BookingRequestViewModel> result = bookingController.findById(id);
        Assertions.assertEquals(result.getBody(),expected);
    }
    @Test
    public void testCreateBookingRequest() throws Exception {        
        BookingRequestRequestModel booking = new BookingRequestRequestModel();
        booking.setId(1);
        booking.setStudentId(89);
        booking.setTopicId(3);
        booking.setStatus(1);
             
        BookingRequestViewModel expected = new BookingRequestViewModel();
        expected.setId(1);
        expected.setStudentId(89);
        expected.setTopicId(4);
        expected.setStatus(2);
        
        when(bookingService.createBookingRequest(booking)).thenReturn(expected);
        ResponseEntity<BookingRequestViewModel> result = bookingController.createBookingRequest(booking);
        Assertions.assertEquals(result.getBody(),expected);
    }
    
     @Test
    public void testUpdateBookingRequest() throws Exception {        
        BookingRequestRequestModel booking = new BookingRequestRequestModel();
        booking.setId(1);
        booking.setStudentId(89);
        booking.setTopicId(3);
        booking.setStatus(1);
             
        BookingRequestViewModel expected = new BookingRequestViewModel();
        expected.setId(1);
        expected.setStudentId(89);
        expected.setTopicId(4);
        expected.setStatus(2);
        
        when(bookingService.updateBookingRequest(booking)).thenReturn(expected);
        ResponseEntity<BookingRequestViewModel> result = bookingController.updateBookingRequest(booking);
        Assertions.assertEquals(result.getBody(),expected);
    }
    
    
}
