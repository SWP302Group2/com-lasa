/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.controllers.FavoriteLecturerOperations;
import com.lasa.business.controllers.utils.authorization.IsNotAvailable;
import com.lasa.business.services.FavoriteLecturerService;
import com.lasa.data.model.entity.key.FavoriteLecturerKey;
import com.lasa.data.model.request.FavoriteLecturerRequestModel;
import com.lasa.data.model.utils.criteria.FavoriteLecturerSearchCriteria;
import com.lasa.data.model.view.FavoriteLecturerViewModel;
import com.lasa.data.validator.model.ValidList;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    public ResponseEntity<List<FavoriteLecturerViewModel>> createFavoriteLecturer(ValidList<FavoriteLecturerRequestModel> favoriteLecturers) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(favoriteLecturerService.addFavoriteLecturers(favoriteLecturers));
    }

    @Override
    @IsNotAvailable
    public ResponseEntity deleteFavoriteLecturers(List<FavoriteLecturerKey> ids) {
        favoriteLecturerService.deleteFavoriteLecturers(ids);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<?> findWithArguments(@Valid FavoriteLecturerSearchCriteria searchCriteria) {
        if(Objects.nonNull(searchCriteria.getTopLecturer()))
            return ResponseEntity.ok(favoriteLecturerService.findTopFavoriteLecturer(searchCriteria.getTopLecturer()));

        return ResponseEntity.ok(favoriteLecturerService.findAllLecturerAndStudentInFavoriteLecturer());
    }
    
}
