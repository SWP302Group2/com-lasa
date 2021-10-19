/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.controllers.FavoriteLecturerOperations;
import com.lasa.business.services.FavoriteLecturerService;
import com.lasa.data.entity.FavoriteLecturer;
import com.lasa.data.entity.key.FavoriteLecturerKey;
import com.lasa.data.entity.utils.criteria.FavoriteLecturerSearchCriteria;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author hai
 */
@RestController
@RequestMapping("api/v1/favorite-lecturers")
@Api(value = "favorite-lecturers", description = "About favorite lecturers", tags = { "Favorite Lecturers Controller" })
public class FavoriteLecturerController implements FavoriteLecturerOperations {

    private final FavoriteLecturerService favoriteLecturerService;

    @Autowired
    public FavoriteLecturerController(@Qualifier("FavoriteLecturerServiceImplV1") FavoriteLecturerService favoriteLecturerService) {
        this.favoriteLecturerService = favoriteLecturerService;
    }

    @Override
    public List<FavoriteLecturer> createFavoriteLecturer(List<FavoriteLecturer> favoriteLecturers) {
        return favoriteLecturerService.addFavoriteLecturers(favoriteLecturers);
    }

    @Override
    public void deleteFavoriteLecturers(List<FavoriteLecturerKey> ids) {
        favoriteLecturerService.deleteFavoriteLecturers(ids);
    }

    @Override
    public ResponseEntity<?> findWithArguments(FavoriteLecturerSearchCriteria searchCriteria) {
        if(Objects.nonNull(searchCriteria.getTopLecturer()))
            return ResponseEntity.ok(favoriteLecturerService.findTopFavoriteLecturer(searchCriteria.getTopLecturer()));

        return ResponseEntity.ok(favoriteLecturerService.findAllLecturerAndStudentInFavoriteLecturer());
    }
    
}
