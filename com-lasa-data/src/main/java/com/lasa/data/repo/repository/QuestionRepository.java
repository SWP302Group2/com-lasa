/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repo.repository;

import com.lasa.data.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author hai
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>, JpaSpecificationExecutor<Question> {

    @Query("select count(q) from Question q where q.bookingRequest.id = ?1 and q.id in ?2")
    long countByIdAndBookingRequestIdIn(Integer bookingId, List<Integer> questionId);

    long countByBookingRequestId(Integer bookingId);

    @Query("select count(q) from Question q join q.bookingRequest as b where q.id in ?1 and b.status = 1 and b.studentId = ?2")
    long countAvailableQuestionsForDelete(List<Integer> ids, Integer studentId);

}
