package com.lasa.business.services;

import com.lasa.business.services.implv1.LecturerTopicDetailServiceImpl;
import com.lasa.data.model.entity.Lecturer;
import com.lasa.data.model.entity.LecturerTopicDetail;
import com.lasa.data.model.entity.Topic;
import com.lasa.data.model.entity.key.LecturerTopicDetailKey;
import com.lasa.data.model.view.LecturerTopicDetailViewModel;
import com.lasa.data.repo.repository.LecturerTopicDetailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class LecturerTopicDetailServiceTest {

    @Mock
    private LecturerTopicDetailRepository lecturerTopicDetailRepository;
    @InjectMocks
    private LecturerTopicDetailServiceImpl lecturerTopicDetailService;

    private LecturerTopicDetail lecturerTopicDetail;
    @BeforeEach
    void setUp() {
        lecturerTopicDetailService = new LecturerTopicDetailServiceImpl(lecturerTopicDetailRepository);

        lecturerTopicDetail = new LecturerTopicDetail();
        lecturerTopicDetail.setLecturer(Lecturer.builder().id(1).build());
        lecturerTopicDetail.setTopic(Topic.builder().id(1).build());
        lecturerTopicDetail.setId(LecturerTopicDetailKey.builder().lecturerId(1).topicId(1).build());
    }

    @Test
    void returnListLecturerAndTopicInLecturerTopicDetail() {
        List<LecturerTopicDetailViewModel> listView = new ArrayList<>();
        LecturerTopicDetailViewModel lecturerTopicDetailViewModel = new LecturerTopicDetailViewModel(lecturerTopicDetail);
        listView.add(lecturerTopicDetailViewModel);

        List<LecturerTopicDetail> topicDetailList = new ArrayList<>();
        topicDetailList.add(lecturerTopicDetail);
        when(lecturerTopicDetailRepository.findAllLecturerAndTopicInLecturerTopicDetail()).thenReturn(topicDetailList);
        List<LecturerTopicDetailViewModel> result =
                lecturerTopicDetailService.findAllLecturerAndTopicInLecturerTopicDetail();
        assertEquals(result.size(), 1);
    }

    @Test
    void shouldReturnListTopicIdByLecturerIdSizeIsTwo() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        when(lecturerTopicDetailRepository.findTopicIdsByLecturerId(1)).thenReturn(ids);
        List<Integer> result = lecturerTopicDetailService.findListTopicIdByLecturerId(1);
        assertEquals(result.size(), 2);
    }

    @Test
    void findById() {

    }

    @Test
    void shouldCreateLecturerTopicDetails() {
        List<LecturerTopicDetail> list = new ArrayList<>();
        list.add(lecturerTopicDetail);
        lecturerTopicDetailService.createLecturerTopicDetails(list);
        Mockito.verify(lecturerTopicDetailRepository).saveAll(list);
    }

    @Test
    void updateLecturerTopicDetails() {
    }

    @Test
    void shouldDeleteLecturerTopicDetails() {
        List<LecturerTopicDetailKey> ids = new ArrayList<>();
        ids.add(LecturerTopicDetailKey.builder().topicId(1).lecturerId(1).build());
        lecturerTopicDetailService.deleteLecturerTopicDetails(ids);
        Mockito.verify(lecturerTopicDetailRepository).deleteAllById(ids);
    }
}