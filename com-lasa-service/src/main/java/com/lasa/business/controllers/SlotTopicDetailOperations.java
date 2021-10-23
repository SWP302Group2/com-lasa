/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.SlotTopicDetail;
import com.lasa.data.entity.key.SlotTopicDetailKey;
import com.lasa.data.entity.utils.criteria.SlotTopicDetailSearchCriteria;
import com.lasa.data.entity.utils.page.SlotTopicDetailPage;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Admin
 */
@RequestMapping("/default")
public interface SlotTopicDetailOperations {

    @GetMapping
    ResponseEntity<?> findWithArguments(SlotTopicDetailPage slotTopicDetailPage, SlotTopicDetailSearchCriteria searchCriteria);

    @GetMapping(value = "/{id}")
    SlotTopicDetail findById(
            @ApiParam(name = "id", type = "Integer", value = "By id, you may find a slot topic.", required = true)
            @PathVariable SlotTopicDetailKey id);

    @PostMapping
    List<SlotTopicDetail> createSlotTopicDetails(
            @ApiParam(name = "details", type = "body", value = "Add topic of slot", required = true)
            @RequestBody List<SlotTopicDetail> details);

    @PutMapping
    List<SlotTopicDetail> updateSlotTopicDetails(
            @ApiParam(name = "details", type = "body", value = "Update a slot topic by id", required = true)
            @RequestBody List<SlotTopicDetail> details);

    @DeleteMapping
    void deleteSlotTopicDetails(
            @ApiParam(name = "ids", type = "body", value = "Remove the slot's topic by id", required = true)
            @RequestBody List<SlotTopicDetailKey> ids);
}
