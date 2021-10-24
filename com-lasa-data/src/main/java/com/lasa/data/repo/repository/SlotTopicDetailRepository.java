/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repo.repository;

import com.lasa.data.model.entity.SlotTopicDetail;
import com.lasa.data.model.entity.key.SlotTopicDetailKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("FROM SlotTopicDetail as s JOIN FETCH s.topic JOIN FETCH s.slot where s.slot.id in ?1")
    List<SlotTopicDetail> findAllSlotAndTopicBySlotId(List<Integer> id);

    @Query("FROM SlotTopicDetail as s JOIN FETCH s.topic JOIN FETCH s.slot where s.topic.id in ?1")
    List<SlotTopicDetail> findAllSlotAndTopicByTopicId(List<Integer> id);

    @Query("FROM SlotTopicDetail as s JOIN FETCH s.topic JOIN FETCH s.slot where s.slot.id in ?1 and s.topic.id in ?2")
    List<SlotTopicDetail> findAllSlotAndTopicByTopicIdAndSlotId(List<Integer> slotIds, List<Integer> topicIds);

    @Query("FROM SlotTopicDetail as s JOIN FETCH s.topic JOIN FETCH s.slot")
    List<SlotTopicDetail> findAllSlotAndTopic();

    @Query(
            value = "FROM SlotTopicDetail as s JOIN FETCH s.topic JOIN FETCH s.slot where s.slot.id in ?1",
            countQuery = "SELECT COUNT(s) FROM SlotTopicDetail as s  where s.slot.id in ?1"
    )
    Page<SlotTopicDetail> findAllSlotAndTopicBySlotId(List<Integer> id, Pageable pageable);

    @Query(
            value = "FROM SlotTopicDetail as s JOIN FETCH s.topic JOIN FETCH s.slot where s.topic.id in ?1",
            countQuery = "SELECT COUNT(s) FROM SlotTopicDetail as s where s.topic.id in ?1"
    )
    Page<SlotTopicDetail> findAllSlotAndTopicByTopicId(List<Integer> id, Pageable pageable);

    @Query(
            value = "FROM SlotTopicDetail as s JOIN FETCH s.topic JOIN FETCH s.slot where s.slot.id in ?1 and s.topic.id in ?2",
            countQuery = "SELECT COUNT(s) FROM SlotTopicDetail as s where s.slot.id in ?1 and s.topic.id in ?2"
    )
    Page<SlotTopicDetail> findAllSlotAndTopicByTopicIdAndSlotId(List<Integer> slotIds, List<Integer> topicIds, Pageable pageable);

    @Query(
            value = "FROM SlotTopicDetail as s JOIN FETCH s.topic JOIN FETCH s.slot",
            countQuery = "SELECT COUNT(s) FROM SlotTopicDetail as s"
    )
    Page<SlotTopicDetail> findAllSlotAndTopic(Pageable pageable);

}
