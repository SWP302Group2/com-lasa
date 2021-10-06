/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repository;

import java.util.List;

import com.lasa.data.entity.LecturerTopicDetail;
import com.lasa.data.entity.key.LecturerTopicDetailKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hai
 */
@Repository
public interface LecturerTopicDetailRepository extends JpaRepository<LecturerTopicDetail, LecturerTopicDetailKey>{
    
    @Query("FROM LecturerTopicDetail l JOIN FETCH l.lecturer JOIN FETCH l.topic")
    public List<LecturerTopicDetail> findAllLecturerAndTopicInLecturerTopicDetail();
}
