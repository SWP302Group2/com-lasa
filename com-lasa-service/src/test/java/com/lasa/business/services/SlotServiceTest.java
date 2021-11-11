package com.lasa.business.services;

import com.lasa.business.services.implv1.LecturerServiceImpl;
import com.lasa.business.services.implv1.SlotServiceImpl;
import com.lasa.data.model.entity.BookingRequest;
import com.lasa.data.model.entity.Slot;
import com.lasa.data.model.request.SlotBookingRequestModel;
import com.lasa.data.model.request.SlotRequestModel;
import com.lasa.data.model.view.SlotViewModel;
import com.lasa.data.repo.repository.BookingRequestRepository;
import com.lasa.data.repo.repository.SlotRepository;
import com.lasa.data.repo.repository.SlotTopicDetailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class SlotServiceTest {

    @Mock
    private SlotRepository slotRepository;
    @Mock
    private SlotTopicDetailService slotTopicDetailService;
    @Mock
    private LecturerServiceImpl lecturerService;
    @Mock
    private BookingRequestRepository bookingRequestRepository;

    @InjectMocks
    private SlotServiceImpl slotService;

    private SlotRequestModel slotRequestModel;

    @BeforeEach
    void setup(){
    slotService = new SlotServiceImpl(slotRepository,lecturerService, slotTopicDetailService,
            bookingRequestRepository);

        slotRequestModel = new SlotRequestModel();
        slotRequestModel.setId(1);
        slotRequestModel.setLecturerId(1);
        slotRequestModel.setStatus(1);

    }
    @Test
    void findWithArguments() {
    }

    @Test
    void testFindWithArguments() {
    }

    @Test
    void shouldReturnSlotIdWhenFindById() {
        Optional<Slot> slotOptional = Optional.of(slotRequestModel.toEntity());
        when(slotRepository.findById(1))
                .thenReturn(slotOptional);
        SlotViewModel result = slotService.findById(1);
        assertEquals(1, result.getId());
    }

    @Test
    void shouldReturnTrueWhenVerifySlotForDelete() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        Long list = Long.valueOf(ids.size());

        when(slotRepository.countAvailableDeleteSlot(ids, slotRequestModel.toEntity().getLecturerId()))
                .thenReturn(list);
        boolean result = slotService.verifySlotForDelete(ids,slotRequestModel.toEntity().getLecturerId());
        assertTrue(result);
    }

    @Test
    void shouldCreateSlot() {
        when(slotRepository.save(slotRequestModel.toEntity()))
                .thenReturn(slotRequestModel.toEntity());
        SlotViewModel result = slotService.createSlot(slotRequestModel);
        assertEquals(1, result.getId());
    }

    @Test
    void updateSlots() {
    }

    @Test
    void acceptDenyBooking() {
//        Optional<Slot> slotOptional = Optional.of(slotRequestModel.toEntity());
//        when(slotRepository.findById(1))
//                .thenReturn(slotOptional);
//
//        SlotBookingRequestModel requestModel = slotOptional.map(t -> new SlotBookingRequestModel()).get();
//        System.out.println(requestModel.getLecturerId());
//        SlotViewModel view = slotService.acceptDenyBooking(requestModel);
    }

    @Test
    void shouldDeleteSlots() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        slotService.deleteSlots(ids);
        verify(slotRepository).findAllById(ids);
    }
}