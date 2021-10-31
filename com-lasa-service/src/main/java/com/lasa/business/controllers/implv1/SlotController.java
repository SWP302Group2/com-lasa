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
import com.lasa.data.model.request.SlotBookingRequestModel;
import com.lasa.data.model.request.SlotRequestModel;
import com.lasa.data.model.request.SlotTopicDetailRequestModel;
import com.lasa.data.model.view.BookingRequestViewModel;
import com.lasa.data.model.view.SlotViewModel;
import com.lasa.data.model.utils.criteria.BookingRequestSearchCriteria;
import com.lasa.data.model.utils.criteria.SlotSearchCriteria;
import com.lasa.data.model.utils.page.SlotPage;
import com.lasa.security.appuser.MyUserDetails;
import com.lasa.security.utils.exception.ExceptionUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
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
    @PreAuthorize("#slotRequestModel.lecturerId = authentication.principal.id")
    public ResponseEntity<SlotViewModel> createSlot(SlotRequestModel slotRequestModel){
        List<Integer> topicIds;

        if(Objects.isNull(slotRequestModel.getTopics()))
            topicIds = lecturerTopicDetailService.findListTopicIdByLecturerId(slotRequestModel.getLecturerId());
        else
            topicIds = slotRequestModel.getTopics();

        slotRequestModel.setStatus(1);
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
    @IsLecturer
    @PreAuthorize("#slotRequestModel.lecturerId = authentication.principal.id")
    public ResponseEntity<SlotViewModel> updateSlots(SlotRequestModel slotRequestModel) {
        return ResponseEntity.ok(slotService.updateSlots(slotRequestModel));
    }

    @Override
    @IsLecturer
    @PreAuthorize("(#model.lecturerId = authentication.principal.id) && (#id = authentication.principal.id)")
    public ResponseEntity<SlotViewModel> updateBookingRequests(Integer id, SlotBookingRequestModel model) {
        return ResponseEntity.ok(slotService.acceptDenyBooking(model));
    }

    @Override
    public void deleteSlots(List<Integer> ids) {
        slotService.deleteSlots(ids);
    }

}
