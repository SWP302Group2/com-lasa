package com.lasa.business.services;

import com.lasa.business.services.implv1.SlotTopicDetailServiceImpl;
import com.lasa.data.model.entity.SlotTopicDetail;
import com.lasa.data.model.entity.key.SlotTopicDetailKey;
import com.lasa.data.model.request.SlotTopicDetailRequestModel;
import com.lasa.data.model.view.SlotTopicDetailViewModel;
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
class SlotTopicDetailServiceTest {

    @Mock
    private SlotTopicDetailRepository slotTopicDetailRepository;
    @InjectMocks
    private SlotTopicDetailServiceImpl slotTopicDetailService;

    private List<SlotTopicDetail> topics;
    private SlotTopicDetail slotTopicDetail;
    private SlotTopicDetailKey slotTopicDetailKey;
    private List<SlotTopicDetailRequestModel> details;
    private SlotTopicDetailRequestModel slotTopicDetailRequestModel;
    @BeforeEach
    void setUp() {
        slotTopicDetailService = new SlotTopicDetailServiceImpl(slotTopicDetailRepository);

        slotTopicDetailKey = new SlotTopicDetailKey();
        slotTopicDetailKey.setTopicId(1);
        slotTopicDetailKey.setSlotId(1);

        slotTopicDetail = new SlotTopicDetail();
        slotTopicDetail.setId(slotTopicDetailKey);

        slotTopicDetailRequestModel = new SlotTopicDetailRequestModel();
        slotTopicDetailRequestModel.setSlotId(1);
        slotTopicDetailRequestModel.setTopicId(1);

        topics = new ArrayList<>();
        topics.add(slotTopicDetailRequestModel.toEntity());

        details = new ArrayList<>();
        details.add(slotTopicDetailRequestModel);
    }

    @Test
    void findAllWithArgument() {
    }

    @Test
    void testFindAllWithArgument() {
    }

    @Test
    void findById() {
        // null
    }

    @Test
    void shouldCreateSlotTopicDetails() {
        when(slotTopicDetailRepository.saveAll(topics))
                .thenReturn(topics);
        List<SlotTopicDetailViewModel> result = slotTopicDetailService.createSlotTopicDetails(details);
        assertEquals(1, result.get(0).getSlotId());
    }

    @Test
    void updateSlotTopicDetails() {
        // Null
    }

    @Test
    void shouldDeleteSlotTopicDetails() {
        List<SlotTopicDetailKey> ids = new ArrayList<>();
        ids.add(slotTopicDetailKey);

        slotTopicDetailService.deleteSlotTopicDetails(ids);
        verify(slotTopicDetailRepository).deleteAllById(ids);
    }
}