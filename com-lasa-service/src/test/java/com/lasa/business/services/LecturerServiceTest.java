package com.lasa.business.services;

import com.lasa.business.services.implv1.EmailSenderServiceImpl;
import com.lasa.business.services.implv1.LecturerServiceImpl;
import com.lasa.data.model.entity.Lecturer;
import com.lasa.data.model.request.LecturerRequestModel;
import com.lasa.data.model.view.LecturerViewModel;
import com.lasa.data.repo.repository.FavoriteLecturerRepository;
import com.lasa.data.repo.repository.LecturerRepository;
import com.lasa.data.repo.repository.LecturerTopicDetailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class LecturerServiceTest {

    @Mock
    private LecturerRepository lecturerRepository;
    @Mock
    private FavoriteLecturerRepository favoriteLecturerRepository;
    @Mock
    private LecturerTopicDetailRepository lecturerTopicDetailRepository;

    @InjectMocks
    private LecturerServiceImpl lecturerService;

    @InjectMocks
    private EmailSenderServiceImpl emailSenderService;


    private List<Integer> topics;
    private Lecturer lecturer;
    private LecturerRequestModel lecturerRequestModel;
    @BeforeEach
    void setUp() {
        lecturerService = new LecturerServiceImpl(lecturerRepository, favoriteLecturerRepository, lecturerTopicDetailRepository);

        topics = new ArrayList<>();
        topics.add(1);

        LocalDate now = LocalDate.now();
        lecturerRequestModel = new LecturerRequestModel
                (1,"abc","0909123456",
                        "xyz",1,1, now,
                        "dia chi", "avatar", topics);
    }

    @Test
    void findAll() {
//        List<Integer> lecIds = new ArrayList();
//        lecIds.add(1);
//
//        LecturerSearchCriteria searchCriteria = new LecturerSearchCriteria
//                ("tungvuive@gmail.com",
//                        "abc",
//                        "0909123456",
//                        1,
//                        true,
//                        "dayladiachi",
//                        lecIds );
//
//        List<Lecturer> lecturers = new ArrayList<>();
//        lecturers.add(lecturer);
//
//        LecturerPage lecturerPage = new LecturerPage();
//        lecturerPage.setPage(1);
//        lecturerPage.setPaging(true);
//        lecturerPage.setOrderBy(Sort.Direction.ASC);
//        lecturerPage.setSize(10);
//        lecturerPage.setSortBy(lecturers.get(0).getEmail());
//
//        List<LecturerViewModel> lecturerViewModelList = lecturers.stream()
//                .map(t -> new LecturerViewModel(t))
//                .collect(Collectors.toList());

//
//        List<Lecturer> lecturerList =
//                lecturerRepository.findAll(LecturerSpecification.searchSpecification(searchCriteria));
//        when(lecturerViewModelList.get(0)).thenReturn(lecturerViewModelList.get(0));

//        List<LecturerViewModel> result = lecturerService.findAll(searchCriteria);
//
//
//        assertEquals(result,lecturerViewModelList);
        }

    @Test
    void testFindAll() {
    }

    @Test
    void shouldCreateLecturerIdIs1() {
        lecturer = lecturerRequestModel.toEntity();
        when(lecturerRepository.save(lecturer))
                .thenReturn(lecturer);
        LecturerViewModel result  = lecturerService.createLecturer(lecturerRequestModel);
        assertEquals(1, result.getId());
    }

    @Test
    void shouldReturnPhoneNumberFindByLecturerId() {
        Optional<Lecturer> optionalLecturer = Optional.of(lecturerRequestModel.toEntity());
        when(lecturerRepository.findById(1))
                .thenReturn(optionalLecturer);
        LecturerViewModel result = lecturerService.findLecturerById(1);
        assertEquals("0909123456",result.getPhone());
    }

    @Test
    void updateLecturer() {
    }
}