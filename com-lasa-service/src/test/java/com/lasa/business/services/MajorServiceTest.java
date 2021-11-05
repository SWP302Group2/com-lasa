package com.lasa.business.services;

import com.lasa.business.services.implv1.MajorServiceImpl;
import com.lasa.data.model.entity.Major;
import com.lasa.data.model.request.MajorRequestModel;
import com.lasa.data.model.utils.projection.MajorWithSimpleTopic;
import com.lasa.data.model.view.MajorViewModel;
import com.lasa.data.repo.repository.MajorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class MajorServiceTest {

    @Mock
    private MajorRepository majorRepository;

    @InjectMocks
    private MajorServiceImpl majorService;

    private MajorWithSimpleTopic majorWithSimpleTopic;
    private MajorWithSimpleTopic.SimpleTopic simpleTopic;
    private Collection<MajorWithSimpleTopic.SimpleTopic> topicCollection;
    private Major major;
    private List<MajorRequestModel> majorModels;
    private MajorRequestModel majorRequestModel;
    @BeforeEach
    void setUp() {
        majorService = new MajorServiceImpl(majorRepository);

        simpleTopic = new MajorWithSimpleTopic.SimpleTopic() {
            @Override
            public Integer getId() {
                return 1;
            }

            @Override
            public String getName() {
                return "THIS IS AMERICA";
            }

            @Override
            public String getCourseId() {
                return "HISTORY";
            }
        };
        majorWithSimpleTopic = new MajorWithSimpleTopic() {
            @Override
            public String getId() {
                return "number1";
            }

            @Override
            public String getName() {
                return "yomost";
            }

            @Override
            public Collection<SimpleTopic> getTopics() {
                topicCollection = new ArrayList<>();
                topicCollection.add(simpleTopic);
                return  topicCollection;
            }
        };

        major = new Major();
        major.builder()
                .id("number1")
                .name("hahaha")
                .build();
        Collection<Integer> topicsIntegerCollection = new ArrayList<>();
        topicsIntegerCollection.add(1);
        topicsIntegerCollection.add(2);
        majorRequestModel = new MajorRequestModel("number 2", "funny", "today is sunday",topicsIntegerCollection);

        majorModels = new ArrayList<>();
        majorModels.add(majorRequestModel);
    }

    @Test
    void findAll() {
    }

    @Test
    void testFindAll() {
    }

    @Test
    void findAllWithTopicIds() {

        List<MajorWithSimpleTopic> simpleTopicList = new ArrayList<>();
        simpleTopicList.add(majorWithSimpleTopic);
    when(majorRepository.findBy(MajorWithSimpleTopic.class))
            .thenReturn(simpleTopicList);
        List<MajorWithSimpleTopic> result =  majorService.findAllWithTopicIds();
    assertEquals("number1", result.get(0).getId());
    }

    @Test
    void findById() {

        Optional<Major> optionalMajor = Optional.of(major);
        when(majorRepository.findById("number1"))
                .thenReturn(optionalMajor);
        MajorViewModel result = majorService.findById("number1");
        assertEquals("number1",result.getId());
    }

    @Test
    void createMajors() {
//
//        List<Major> majors = majorModels.stream()
//                .map(t -> t.toEntity())
//                .collect(Collectors.toList());
//        when(majorRepository.saveAll(majors
//                        .stream()
//                        .map(t -> MajorViewModel))
//                .thenReturn(majors
//                        .stream()
//                        .map(t -> new MajorViewModel())
//                        .collect(Collectors.toList()));
//        majorService.createMajors(majorModels);
    }

    @Test
    void updateMajors() {

    }

    @Test
    void deleteMajors() {
        List<String> ids = new ArrayList<>();
        ids.add("number1");
        ids.add("number2");
        majorService.deleteMajors(ids);
        verify(majorRepository).deleteAllById(ids);
    }
}