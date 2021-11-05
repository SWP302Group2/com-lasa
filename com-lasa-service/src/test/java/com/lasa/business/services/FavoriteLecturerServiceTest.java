package com.lasa.business.services;

import com.lasa.business.services.implv1.FavoriteLecturerServiceImpl;
import com.lasa.data.model.entity.FavoriteLecturer;
import com.lasa.data.model.entity.Lecturer;
import com.lasa.data.model.entity.Student;
import com.lasa.data.model.entity.key.FavoriteLecturerKey;
import com.lasa.data.model.request.FavoriteLecturerRequestModel;
import com.lasa.data.model.view.FavoriteLecturerViewModel;
import com.lasa.data.model.view.LecturerViewModel;
import com.lasa.data.repo.repository.FavoriteLecturerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith({SpringExtension.class})
class FavoriteLecturerServiceTest {
    @Mock
    private FavoriteLecturerRepository favoriteLecturerRepository;
    @InjectMocks
    private FavoriteLecturerServiceImpl favoriteLecturerService;

    private FavoriteLecturerRequestModel favoriteLecturerRequestModel;
    private FavoriteLecturer favoriteLecturer;
    private LecturerViewModel lecturerViewModel;

    private List<FavoriteLecturer> favoriteLecturerList;
    private List<FavoriteLecturerRequestModel> favoriteLecturerRequestModelList;
    private List<LecturerViewModel> lecturerViewModelList;
    private List<Lecturer> lecturerList;

    @BeforeEach
    void setUp() {
        favoriteLecturerService = new FavoriteLecturerServiceImpl(favoriteLecturerRepository);

        favoriteLecturerRequestModel = new FavoriteLecturerRequestModel();
        favoriteLecturerRequestModel.toEntity(1,1);

        favoriteLecturerRequestModelList = new ArrayList<>();
        favoriteLecturerRequestModelList.add(favoriteLecturerRequestModel);

        favoriteLecturer = new FavoriteLecturer();
        favoriteLecturer.setLecturer(Lecturer.builder().id(1).build());
        favoriteLecturer.setStudent(Student.builder().id(1).build());
        favoriteLecturer.setId(FavoriteLecturerKey.builder().lecturerId(1).studentId(1).build());

        favoriteLecturerList = new ArrayList<>();
        favoriteLecturerList.add(favoriteLecturer);

        lecturerList = new ArrayList<>();
        lecturerList.add(Lecturer.builder().id(1).build());
    }

    @Test
    void findAllLecturerAndStudentInFavoriteLecturer() {
        favoriteLecturerService.findAllLecturerAndStudentInFavoriteLecturer();
        Mockito.verify(favoriteLecturerRepository)
                .findAllLecturerAndStudentInFavoriteLecturer();

        when(favoriteLecturerRepository.findAllLecturerAndStudentInFavoriteLecturer())
                .thenReturn(favoriteLecturerList);

        List<FavoriteLecturerViewModel> result = favoriteLecturerService.findAllLecturerAndStudentInFavoriteLecturer();
        assertEquals(result.get(0).getLecturer(), favoriteLecturer.getLecturer() );
    }

    @Test
    void findTopFavoriteLecturer() {
        favoriteLecturerService.findTopFavoriteLecturer(1);
        Mockito.verify(favoriteLecturerRepository)
                .findTopFavoriteLecturer(1);

        when(favoriteLecturerRepository.findTopFavoriteLecturer(2))
                .thenReturn(lecturerList);
        lecturerViewModelList = favoriteLecturerService.findTopFavoriteLecturer(2);

        assertEquals(lecturerViewModelList.get(0).getId(), lecturerList.get(0).getId() );
    }

    @Test
    void addFavoriteLecturers() {

        List<FavoriteLecturer> favoriteLecturers = favoriteLecturerRequestModelList.stream()
                .map(t -> t.toEntity())
                .collect(Collectors.toList());

        favoriteLecturerService.addFavoriteLecturers(favoriteLecturerRequestModelList);

        Mockito.verify(favoriteLecturerRepository)
                .saveAll(favoriteLecturers);
    }
}