/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services.implv1;

import com.lasa.business.services.FavoriteLecturerService;
import com.lasa.data.model.entity.FavoriteLecturer;
import com.lasa.data.model.request.FavoriteLecturerRequestModel;
import com.lasa.data.model.view.FavoriteLecturerViewModel;
import com.lasa.data.model.view.LecturerViewModel;
import com.lasa.data.model.entity.key.FavoriteLecturerKey;
import com.lasa.data.repo.repository.FavoriteLecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author hai
 */
@Component
@Qualifier("FavoriteLecturerServiceImplV1")
public class FavoriteLecturerServiceImpl implements FavoriteLecturerService {
    
    private final FavoriteLecturerRepository favoriteLecturerRepo;

    @Autowired
    public FavoriteLecturerServiceImpl(FavoriteLecturerRepository favoriteLecturerRepo) {
        this.favoriteLecturerRepo = favoriteLecturerRepo;
    }

    @Override
    public List<FavoriteLecturerViewModel> findAllLecturerAndStudentInFavoriteLecturer() {
        return favoriteLecturerRepo
                .findAllLecturerAndStudentInFavoriteLecturer()
                .stream()
                .map(t -> new FavoriteLecturerViewModel(t))
                .collect(Collectors.toList());
    }

    @Override
    public List<LecturerViewModel> findTopFavoriteLecturer(Integer topNumber) {
        return favoriteLecturerRepo
                .findTopFavoriteLecturer(topNumber)
                .stream()
                .map(t -> new LecturerViewModel(t))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFavoriteLecturers(List<FavoriteLecturerKey> ids) {
        favoriteLecturerRepo.deleteAllById(ids);
    }

    @Override
    public List<FavoriteLecturerViewModel> addFavoriteLecturers(List<FavoriteLecturerRequestModel> model) {
        List<FavoriteLecturer> favoriteLecturers = model.stream()
                .map(t -> t.toEntity())
                .collect(Collectors.toList());
        return favoriteLecturerRepo.saveAll(favoriteLecturers)
                .stream()
                .map(t -> new FavoriteLecturerViewModel(t))
                .collect(Collectors.toList());
    }

}
