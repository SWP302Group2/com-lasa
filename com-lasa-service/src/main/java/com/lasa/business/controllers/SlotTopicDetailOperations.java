/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.SlotTopicDetail;
import com.lasa.data.entity.key.SlotTopicDetailKey;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Admin
 */
@RequestMapping("/default")
public interface SlotTopicDetailOperations {

    @GetMapping
    public List<SlotTopicDetail> findAll();

    @GetMapping(value = "/{id}")
    public SlotTopicDetail findById(@PathVariable SlotTopicDetailKey id);

    @PostMapping
    public List<SlotTopicDetail> createSlotTopicDetails(@RequestBody List<SlotTopicDetail> details);

    @PutMapping
    public List<SlotTopicDetail> updateSlotTopicDetails(@RequestBody List<SlotTopicDetail> details);

    @DeleteMapping
    public void deleteSlotTopicDetails(@RequestBody List<SlotTopicDetailKey> ids);
}
