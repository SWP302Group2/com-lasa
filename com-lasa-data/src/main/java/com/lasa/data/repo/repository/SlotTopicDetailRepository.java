/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repo.repository;

import com.lasa.data.entity.SlotTopicDetail;
import com.lasa.data.entity.key.SlotTopicDetailKey;
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
public interface SlotTopicDetailRepository extends JpaRepository<SlotTopicDetail, SlotTopicDetailKey>, JpaSpecificationExecutor<SlotTopicDetail> {

    @Query("FROM SlotTopicDetail as s JOIN FETCH s.topic where s.slot.id in :id")
    List<SlotTopicDetail> findAllWithTopicBySlotId(List<Integer> id);
}
