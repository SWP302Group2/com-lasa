/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.model.entity.key.FavoriteLecturerKey;
import com.lasa.data.model.request.FavoriteLecturerRequestModel;
import com.lasa.data.model.utils.criteria.FavoriteLecturerSearchCriteria;
import com.lasa.data.validator.model.ValidList;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *
 * @author hai
 */
@RequestMapping("/default")
public interface FavoriteLecturerOperations {
    
    @GetMapping
    ResponseEntity<?> findWithArguments(FavoriteLecturerSearchCriteria searchCriteria);

    @PostMapping
    ResponseEntity<?> createFavoriteLecturer(
            @ApiParam(name = "favoriteLecturers", type = "body", value = "Create a favorite lecturer", required = true)
            @Valid @RequestBody ValidList<FavoriteLecturerRequestModel> favoriteLecturers);
    
    @DeleteMapping
    ResponseEntity<?> deleteFavoriteLecturers(
             @ApiParam(name = "favoriteLecturers", type = "body", value = "Delete a favorite lecturer", required = false)
             @RequestBody List<FavoriteLecturerKey> ids);
}
