/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repo.repository;

import com.lasa.data.model.entity.BookingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @author hai
 */
@Repository
public interface BookingRequestRepository extends JpaRepository<BookingRequest, Integer>, JpaSpecificationExecutor<BookingRequest> {

    @Query("FROM BookingRequest b JOIN FETCH b.questions WHERE b.id = :id")
    Optional<BookingRequest> findByIdAndGetQuestions(Integer id);

    Optional<BookingRequest> findBookingRequestByStudentIdAndSlotId(Integer studentId, Integer slotId);

    long countByStudentIdAndSlotId(Integer studentId, Integer slotId);

}
