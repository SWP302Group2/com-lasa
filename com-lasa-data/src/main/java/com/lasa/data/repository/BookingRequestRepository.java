/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repository;

import java.util.List;
import java.util.Optional;

import com.lasa.data.entity.BookingRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hai
 */
@Repository
public interface BookingRequestRepository extends JpaRepository<BookingRequest, Integer> {

    @Query("FROM BookingRequest b JOIN FETCH b.questions WHERE b.id = :id")
    Optional<BookingRequest> findByIdAndGetQuestions(Integer id);

    Page<BookingRequest> findBookingRequestsByStudentId(Integer id, Pageable pageable);

    Page<BookingRequest> findBookingRequestsBySlotId(Integer id, Pageable pageable);

    Page<BookingRequest> findBookingRequestsByStatus(Integer id, Pageable pageable);
}
