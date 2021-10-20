/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services.implv1;

import com.lasa.business.services.FavoriteLecturerService;
import com.lasa.data.dto.FavoriteLecturerDTO;
import com.lasa.data.dto.LecturerDTO;
import com.lasa.data.entity.FavoriteLecturer;
import com.lasa.data.entity.Lecturer;
import com.lasa.data.entity.key.FavoriteLecturerKey;
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
    public List<FavoriteLecturerDTO> findAllLecturerAndStudentInFavoriteLecturer() {
        return favoriteLecturerRepo
                .findAllLecturerAndStudentInFavoriteLecturer()
                .stream()
                .map(t -> new FavoriteLecturerDTO(t))
                .collect(Collectors.toList());
    }

    @Override
    public List<LecturerDTO> findTopFavoriteLecturer(Integer topNumber) {
        return favoriteLecturerRepo
                .findTopFavoriteLecturer(topNumber)
                .stream()
                .map(t -> new LecturerDTO(t))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFavoriteLecturers(List<FavoriteLecturerKey> ids) {
        favoriteLecturerRepo.deleteAllById(ids);
    }

    @Override
    public List<FavoriteLecturer> addFavoriteLecturers(List<FavoriteLecturer> favoriteLecturers) {
        return favoriteLecturerRepo.saveAll(favoriteLecturers);
    }

}
