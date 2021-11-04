/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repo.repository;

import com.lasa.data.model.entity.LecturerTopicDetail;
import com.lasa.data.model.entity.key.LecturerTopicDetailKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author hai
 */
@Repository
public interface LecturerTopicDetailRepository extends JpaRepository<LecturerTopicDetail, LecturerTopicDetailKey>{
    
    @Query("FROM LecturerTopicDetail l JOIN FETCH l.lecturer JOIN FETCH l.topic")
    List<LecturerTopicDetail> findAllLecturerAndTopicInLecturerTopicDetail();

    @Query("SELECT l.topic.id FROM LecturerTopicDetail l WHERE l.lecturer.id = ?1" )
    List<Integer> findTopicIdsByLecturerId(Integer lecturerId);

    @Query("FROM LecturerTopicDetail l JOIN FETCH l.topic as t WHERE l.lecturer.id = ?1" )
    List<LecturerTopicDetail> findDetailWithTopicsByLecturerId(Integer lecturerId);

    long countByLecturerId(Integer lecturerId);
}
