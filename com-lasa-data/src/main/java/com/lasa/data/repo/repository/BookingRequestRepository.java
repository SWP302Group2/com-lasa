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
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Query(value = "SELECT * FROM BookingRequest b WHERE b.status = 2", nativeQuery = true)
    List<BookingRequest> findAllBookingRequestByStatus();
//
//    @Query(value =
//            "SELECT b.id as booking_id, stu.id as student_id," +
//                    " sl.id as slot_id, stu.email, stu.name as student_name " +
//            "FROM bookingrequest b INNER JOIN student stu ON b.studentid = stu.id  " +
//            "WHERE b.studentid = :bookingRequestId"
//           , nativeQuery = true)
//
//    @Query(value =
//            "SELECT b.id as booking_id, b.slotid, b.status as booking_status, stu.*" +
//
//                    "FROM bookingrequest b INNER JOIN student stu ON b.studentid = stu.id  " +
//                    "WHERE b.studentid = :bookingRequestId"
//            , nativeQuery = true)
//    Optional<?> findByIdAndGetBookingRequestAndSlotAndStudent(@Param("bookingRequestId") Integer bookingRequestId);
    long countByStudentIdAndSlotId(Integer studentId, Integer slotId);

    @Query("select count(b) from BookingRequest b where b.id = ?1 and b.status = 1")
    long countUpdatableBooking(Integer id);

    @Query("select count(b) from BookingRequest b where b.id = ?1 and b.status = 4 and b.rating is null")
    long countRateableBooking(Integer id);

}
