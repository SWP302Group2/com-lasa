/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.controllers.SlotOperations;
import com.lasa.business.controllers.utils.authorization.IsLecturer;
import com.lasa.business.services.BookingRequestService;
import com.lasa.business.services.LecturerTopicDetailService;
import com.lasa.business.services.SlotService;
import com.lasa.business.services.SlotTopicDetailService;
import com.lasa.data.model.request.SlotRequestModel;
import com.lasa.data.model.request.SlotTopicDetailRequestModel;
import com.lasa.data.model.view.BookingRequestViewModel;
import com.lasa.data.model.view.SlotTopicDetailViewModel;
import com.lasa.data.model.view.SlotViewModel;
import com.lasa.data.model.entity.Slot;
import com.lasa.data.model.entity.SlotTopicDetail;
import com.lasa.data.model.entity.Topic;
import com.lasa.data.model.utils.criteria.BookingRequestSearchCriteria;
import com.lasa.data.model.utils.criteria.SlotSearchCriteria;
import com.lasa.data.model.utils.page.SlotPage;
import com.lasa.data.model.view.TopicViewModel;
import com.lasa.security.appuser.MyUserDetails;
import com.lasa.security.utils.exception.ExceptionUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    private final BookingRequestService bookingRequestService;

    @Autowired
    public SlotController(@Qualifier("SlotServiceImplV1") SlotService service,
                          @Qualifier("LecturerTopicDetailServiceImplV1") LecturerTopicDetailService lecturerTopicDetailService,
                          @Qualifier("SlotTopicDetailServiceImplV1") SlotTopicDetailService slotTopicDetailService,
                          @Qualifier("BookingRequestServiceImplV1") BookingRequestService bookingRequestService) {
        this.slotService = service;
        this.lecturerTopicDetailService = lecturerTopicDetailService;
        this.slotTopicDetailService = slotTopicDetailService;
        this.bookingRequestService = bookingRequestService;
    }


    @Override
    public ResponseEntity<?> findWithArguments(SlotSearchCriteria searchCriteria, SlotPage slotPage) throws ExceptionUtils.ArgumentException {
        if(slotPage.isPaging())
            return ResponseEntity.ok(slotService.findWithArguments(searchCriteria, slotPage));
        else {
            return ResponseEntity.ok(slotService.findWithArguments(searchCriteria));
        }
    }

    @Override
    public ResponseEntity<SlotViewModel> findById(Integer id) {
        return ResponseEntity.ok(slotService.findById(id));
    }

    @Override
    public ResponseEntity<?> findByIdIncludeBookingRequests(Integer id, Integer status) {
        SlotViewModel slotDTO = slotService.findById(id);

        if(Objects.isNull(slotDTO))
            return ResponseEntity.ok(null);

        BookingRequestSearchCriteria searchCriteria = new BookingRequestSearchCriteria();
        List<Integer> slotIds = new ArrayList<>();
        slotIds.add(slotDTO.getId());
        searchCriteria.setSlotId(slotIds);

        if(Objects.nonNull(status))
            searchCriteria.setStatus(status);

        List<BookingRequestViewModel> bookingRequestDTOS = bookingRequestService.findAll(searchCriteria);
        bookingRequestDTOS.stream()
                .forEach(t -> slotDTO.addBookingRequest(t));

        return ResponseEntity.ok(slotDTO);
    }

    @Override
    @Transactional
    @IsLecturer
    public ResponseEntity<SlotViewModel> createSlot(SlotRequestModel slotRequestModel) throws ExceptionUtils.ArgumentException {
        List<Integer> topicIds;
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        slotRequestModel.setLecturerId(userDetails.getId());
        slotRequestModel.setStatus(1);

        if(slotRequestModel.getTimeStart().isAfter(slotRequestModel.getTimeEnd()))
            throw new ExceptionUtils.ArgumentException("TIME_START_AFTER_TIME_END");
        else if(slotService.verifySlot(slotRequestModel).equals(false))
            throw new ExceptionUtils.ArgumentException("SLOT_DUPLICATED");
        else if(Objects.isNull(slotRequestModel.getTopics()))
            topicIds = lecturerTopicDetailService.findListTopicIdByLecturerId(slotRequestModel.getLecturerId());
        else
            topicIds = slotRequestModel.getTopics();

        SlotViewModel viewModel = slotService.createSlot(slotRequestModel);
        List<SlotTopicDetailRequestModel> slotTopicDetails = topicIds.stream()
                .map(t -> SlotTopicDetailRequestModel.builder()
                        .slotId(viewModel.getId())
                        .topicId(t)
                        .build()
                ).collect(Collectors.toList());
        slotTopicDetailService.createSlotTopicDetails(slotTopicDetails);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(viewModel);
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
