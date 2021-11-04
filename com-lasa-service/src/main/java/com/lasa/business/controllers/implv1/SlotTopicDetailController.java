/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.controllers.SlotTopicDetailOperations;
import com.lasa.business.services.SlotTopicDetailService;
import com.lasa.data.model.entity.key.SlotTopicDetailKey;
import com.lasa.data.model.request.SlotTopicDetailRequestModel;
import com.lasa.data.model.utils.criteria.SlotTopicDetailSearchCriteria;
import com.lasa.data.model.utils.page.SlotTopicDetailPage;
import com.lasa.data.model.view.SlotTopicDetailViewModel;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api/v1/slot-topic-details")
@Api(value = "slot-topic-details", description = "About topic details for slot", tags = { "Slot topic details" })
public class SlotTopicDetailController implements SlotTopicDetailOperations {

    private final SlotTopicDetailService slotTopicDetailService;

    @Autowired
    public SlotTopicDetailController(@Qualifier("SlotTopicDetailServiceImplV1") SlotTopicDetailService service) {
        this.slotTopicDetailService = service;
    }

    @Override
    public ResponseEntity<?> findWithArguments(SlotTopicDetailPage slotTopicDetailPage, SlotTopicDetailSearchCriteria searchCriteria) {
        if(slotTopicDetailPage.isPaging())
            return ResponseEntity.ok(slotTopicDetailService.findAllWithArgument(slotTopicDetailPage, searchCriteria ));
        else
            return ResponseEntity.ok(slotTopicDetailService.findAllWithArgument(searchCriteria));
    }

    @Override
    public ResponseEntity<SlotTopicDetailViewModel> findById(SlotTopicDetailKey id) {
        return ResponseEntity.ok(slotTopicDetailService.findById(id));
    }

    @Override
    public ResponseEntity<List<SlotTopicDetailViewModel>> createSlotTopicDetails(List<SlotTopicDetailRequestModel> details) {
        return ResponseEntity.ok(slotTopicDetailService.createSlotTopicDetails(details));
    }

    @Override
    public ResponseEntity<List<SlotTopicDetailViewModel>> updateSlotTopicDetails(List<SlotTopicDetailRequestModel> details) {
        return ResponseEntity.ok(slotTopicDetailService.updateSlotTopicDetails(details));
    }

    @Override
    public void deleteSlotTopicDetails(List<SlotTopicDetailKey> ids) {
        slotTopicDetailService.deleteSlotTopicDetails(ids);
    }

}
