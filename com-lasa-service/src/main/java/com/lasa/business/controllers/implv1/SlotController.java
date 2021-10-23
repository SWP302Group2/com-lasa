/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.controllers.SlotOperations;
import com.lasa.business.controllers.utils.authorization.IsLecturer;
import com.lasa.business.services.LecturerTopicDetailService;
import com.lasa.business.services.SlotService;
import com.lasa.business.services.SlotTopicDetailService;
import com.lasa.data.entity.*;
import com.lasa.data.entity.utils.criteria.SlotSearchCriteria;
import com.lasa.data.entity.utils.page.SlotPage;
import com.lasa.security.appuser.MyUserDetails;
import com.lasa.security.utils.exception.ExceptionUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api/v1/slots")
@Api(value = "slots", description = "About slots", tags = { "Slots Controller" })
public class SlotController implements SlotOperations {

    private final SlotService slotService;
    private final LecturerTopicDetailService lecturerTopicDetailService;
    private final SlotTopicDetailService slotTopicDetailService;

    @Autowired
    public SlotController(
            @Qualifier("SlotServiceImplV1") SlotService service,
            @Qualifier("LecturerTopicDetailServiceImplV1") LecturerTopicDetailService lecturerTopicDetailService,
            @Qualifier("SlotTopicDetailServiceImplV1") SlotTopicDetailService slotTopicDetailService
    ) {
        this.slotService = service;
        this.lecturerTopicDetailService = lecturerTopicDetailService;
        this.slotTopicDetailService = slotTopicDetailService;
    }


    @Override
    public ResponseEntity<?> findWithArgument(SlotSearchCriteria searchCriteria, SlotPage slotPage) throws ExceptionUtils.ArgumentException {

        if(slotPage.isPaging())
            return ResponseEntity.ok(slotService.findWithArguments(searchCriteria, slotPage));
        else {
            return ResponseEntity.ok(slotService.findWithArguments(searchCriteria));
        }
    }

    @Override
    @IsLecturer
    public Slot findById(Integer id) {
        return slotService.findById(id);
    }

    @Override
    @Transactional
    public ResponseEntity<Slot> createSlots(Slot slot) throws ExceptionUtils.ArgumentException {
        List<Integer> lecturerIds;
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        slot.setLecturerId(userDetails.getId());

        if(slot.getTimeStart().isAfter(slot.getTimeEnd()))
            throw new ExceptionUtils.ArgumentException("TIME_START_AFTER_TIME_END");
        else if(slotService.verifySlot(slot).equals(false))
            throw new ExceptionUtils.ArgumentException("SLOT_DUPLICATED");
        else if(Objects.isNull(slot.getTopics()))
            lecturerIds = lecturerTopicDetailService.findListTopicIdByLecturerId(slot.getLecturerId());
        else
            lecturerIds = slot.getTopics().stream()
                    .map(t -> t.getTopic().getId())
                    .collect(Collectors.toList());

        slotService.createSlot(slot);
        List<SlotTopicDetail> slotTopicDetails = lecturerIds.stream()
                .map(
                        t -> SlotTopicDetail.builder()
                                    .slot(slot)
                                    .topic(Topic.builder().id(t).build())
                                    .build()
                ).collect(Collectors.toList());

        slotTopicDetailService.createSlotTopicDetails(slotTopicDetails);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(slot);
    }

    @Override
    public List<Slot> updateSlots(List<Slot> slots) {
        return slotService.updateSlots(slots);
    }

    @Override
    public void deleteSlots(List<Integer> ids) {
        slotService.deleteSlots(ids);
    }

}
