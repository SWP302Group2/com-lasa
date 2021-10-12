/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repository;

import com.lasa.data.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


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
}
