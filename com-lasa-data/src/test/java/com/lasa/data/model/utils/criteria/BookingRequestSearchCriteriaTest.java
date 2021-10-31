/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.model.utils.criteria;

import com.lasa.data.model.entity.BookingRequest;
import com.lasa.data.model.utils.specification.BookingRequestSpecification;
import com.lasa.data.repo.repository.BookingRequestRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author MyNhung
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookingRequestSearchCriteriaTest {
    
    private final BookingRequestRepository bookingRepository;
    private final TestEntityManager entityManager;

    @Autowired
    public BookingRequestSearchCriteriaTest(BookingRequestRepository bookingRepository,TestEntityManager entityManager) {
        this.bookingRepository = bookingRepository;
        this.entityManager = entityManager;
    }

    @Test
    public void testBookingSearchCriteriaWithSlotId() {
        List<Integer> searchSlotId = new ArrayList<>();
        searchSlotId.add(1);
        BookingRequestSearchCriteria searchCriteria = new BookingRequestSearchCriteria();
        searchCriteria.setSlotId(searchSlotId);
                
        List<BookingRequest> actualList = bookingRepository.findAll(BookingRequestSpecification.searchSpecification(searchCriteria));

        List<BookingRequest> expectedList = entityManager.getEntityManager()
                .createQuery("select bk from BookingRequest bk where bk.slotId like ?1", BookingRequest.class)
                .setParameter(1,  searchSlotId.get(0))
                .getResultList();
        Assertions.assertEquals(expectedList, actualList);

    }
    @Test
    public void testBookingSearchCriteriaWithSlotIdAndStudentIdArgument() {
        List<Integer> searchSlotId = new ArrayList<>();
        searchSlotId.add(1);
        List<Integer> searchStudentId = new ArrayList<>();
        searchStudentId.add(1);
        
        BookingRequestSearchCriteria searchCriteria = new BookingRequestSearchCriteria();
        searchCriteria.setSlotId(searchSlotId);
        searchCriteria.setStudentId(searchStudentId);   
        
        List<BookingRequest> actualList = bookingRepository.findAll(BookingRequestSpecification.searchSpecification(searchCriteria));
        List<BookingRequest> expectedList = entityManager.getEntityManager()
                .createQuery("select bk from BookingRequest bk where bk.slotId like ?1 and bk.studentId like ?2", BookingRequest.class)
                .setParameter(1, searchSlotId)
                .setParameter(2, searchStudentId)
                .getResultList();
        Assertions.assertEquals(expectedList, actualList);
    }

     @Test
    public void testBookingSearchCriteriaWithStudentIdAndTopicIdArgument() {
        List<Integer> searchTopicId = new ArrayList<>();
        searchTopicId.add(89);
        List<Integer> searchStudentId = new ArrayList<>();
        searchStudentId.add(1);
        
        BookingRequestSearchCriteria searchCriteria = new BookingRequestSearchCriteria();
        searchCriteria.setTopicId(searchTopicId);
        searchCriteria.setStudentId(searchStudentId);   
        
        List<BookingRequest> actualList = bookingRepository.findAll(BookingRequestSpecification.searchSpecification(searchCriteria));
        List<BookingRequest> expectedList = entityManager.getEntityManager()
                .createQuery("select bk from BookingRequest bk where bk.topicId like ?1 and bk.studentId like ?2", BookingRequest.class)
                .setParameter(1, searchTopicId)
                .setParameter(2, searchStudentId)
                .getResultList();
        Assertions.assertEquals(expectedList, actualList);

    }
}
