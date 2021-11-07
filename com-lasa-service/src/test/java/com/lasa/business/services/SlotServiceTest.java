package com.lasa.business.services;

import com.lasa.business.services.implv1.LecturerServiceImpl;
import com.lasa.business.services.implv1.SlotServiceImpl;
import com.lasa.data.model.entity.Slot;
import com.lasa.data.model.request.SlotRequestModel;
import com.lasa.data.model.view.SlotViewModel;
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
    private LecturerTopicDetailService lecturerTopicDetailService;
    @Mock
    private SlotTopicDetailRepository slotTopicDetailRepository;
    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private SlotServiceImpl slotService;

    private SlotRequestModel slotRequestModel;
    private Slot slot;
    @BeforeEach
    void setup(){
    slotService = new SlotServiceImpl(slotRepository,lecturerService, slotTopicDetailService,
            lecturerTopicDetailService, slotTopicDetailRepository, emailSenderService);

        slotRequestModel = new SlotRequestModel();
        slotRequestModel.setId(1);
        slotRequestModel.setLecturerId(1);

        slot = new Slot();
        slot = slotRequestModel.toEntity();
    }
    @Test
    void findWithArguments() {
    }

    @Test
    void testFindWithArguments() {
    }

    @Test
    void findById() {
    }

    @Test
    void verifySlot() {
    }

    @Test
    void shouldCreateSlot() {
        when(slotRepository.save(slotRequestModel.toEntity()))
                .thenReturn(slot);
        SlotViewModel viewModel = slotService.createSlot(slotRequestModel);
        assertEquals(1, viewModel.getId());
    }

    @Test
    void updateSlots() {
    }

    @Test
    void acceptDenyBooking() {
    }

    @Test
    void shouldDeleteSlots() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        slotService.deleteSlots(ids);
        verify(slotRepository).deleteAllById(ids);
    }
}