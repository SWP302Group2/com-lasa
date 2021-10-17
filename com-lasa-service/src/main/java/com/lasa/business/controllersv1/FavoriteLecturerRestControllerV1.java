/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllersv1;

import com.lasa.business.controllers.FavoriteLecturerOperations;
import com.lasa.business.services.FavoriteLecturerService;
import com.lasa.data.entity.FavoriteLecturer;
import com.lasa.data.entity.key.FavoriteLecturerKey;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author hai
 */
@RestController
@RequestMapping("api/v1/favorite-lecturers")
@Api(value = "favorite-lecturers", description = "About favorite lecturers", tags = { "Favorite Lecturers Controller" })
public class FavoriteLecturerRestControllerV1 implements FavoriteLecturerOperations {

    private final FavoriteLecturerService favoriteLecturerService;

    @Autowired
    public FavoriteLecturerRestControllerV1(@Qualifier("FavoriteLecturerServiceImplV1") FavoriteLecturerService favoriteLecturerService) {
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
    public List<FavoriteLecturer> findAll() {
        return favoriteLecturerService.findAllLecturerAndStudentInFavoriteLecturer();
    }
    
}
