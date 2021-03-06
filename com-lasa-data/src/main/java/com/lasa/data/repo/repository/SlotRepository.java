/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repo.repository;

import com.lasa.data.model.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


/**
 *
 * @author hai
 */
@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer>, JpaSpecificationExecutor<Slot> {

    /*@Query(
            value =  "SELECT new com.lasa.data.entity.predicate.SlotWithLecturerAndTopic(sl.id, sl.timeStart, sl.timeEnd, sl.lecturer.id, lec.name) " +
                     "FROM Slot AS sl JOIN sl.lecturer AS lec  WHERE " +
                        "CAST(sl.id as text) like :#{#search.slotId} " +
                        "AND CAST(sl.lecturer.id as text) like :#{#search.lecturerId} " +
                        "AND (sl.timeStart BETWEEN :#{#search.timeStart} AND :#{#search.timeEnd}) " +
                        "AND (sl.timeEnd BETWEEN :#{#search.timeStart} AND :#{#search.timeEnd}) ",
            countQuery = "SELECT count(s) FROM Slot s "
    )
    Page<SlotWithLecturerAndTopic> findWithArgument(@Param("search") SlotSearchCriteria search, Pageable pageable);
*/

    @Query("select count(s) from Slot s where ((s.timeStart >= ?1 and s.timeStart <= ?2) or (s.timeEnd >= ?1 and s.timeEnd <= ?2)) " +
            "and (s.lecturerId = ?3) and (s.status = 1)")
    Integer countActiveSlotByTimeStartAndTimeEndAndLecturerId(LocalDateTime timeStart, LocalDateTime timeEnd, Integer lecturerId);

    @Query("select count(s) from Slot s where s.id = ?1 and s.status = 1")
    long countActiveSlot(Integer id);

    @Query("select count(s) from Slot as s join s.bookingRequests as b where s.id = ?1 and s.lecturerId = ?2 and s.status = 1 and b.id = ?3 and b.status = 1")
    long countAvailableUpdateBookingOfSlot(Integer slotId,Integer lecturerId, Integer bookingId);

    @Query("select count(s) from Slot as s where s.id in ?1 and s.lecturerId = ?2 and (s.status = 0 or s.status = 4)")
    long countAvailableDeleteSlot(List<Integer> id, Integer lecturerId);

}
