/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllersv1;

import com.lasa.business.controllers.FavoriteLecturerOperations;
import com.lasa.data.entity.FavoriteLecturer;
import com.lasa.data.entity.key.FavoriteLecturerKey;
import com.lasa.business.services.FavoriteLecturerService;
import java.util.List;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.*;

/**
 *
 * @author hai
 */
@RestController
@RequestMapping("api/v1/favorite-lecturers")
@CrossOrigin(
        allowCredentials = "true",
        origins = {"http://localhost:3000", "http://localhost:5500", "https://lasa-fpt.web.app"},
        allowedHeaders = {
                CONTENT_TYPE,
                CONTENT_LENGTH,
                HOST,
                USER_AGENT,
                ACCEPT,
                ACCEPT_ENCODING,
                CONNECTION,
                AUTHORIZATION
        },
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS}
)
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
