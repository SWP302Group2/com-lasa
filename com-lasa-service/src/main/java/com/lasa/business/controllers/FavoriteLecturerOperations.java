/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.FavoriteLecturer;
import com.lasa.data.entity.key.FavoriteLecturerKey;
import com.lasa.data.entity.utils.criteria.FavoriteLecturerSearchCriteria;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    List<FavoriteLecturer> createFavoriteLecturer(
            @ApiParam(name = "favoriteLecturers", type = "body", value = "Create a favorite lecturer", required = true)
            @RequestBody List<FavoriteLecturer> favoriteLecturers);
    
    @DeleteMapping
    void deleteFavoriteLecturers(
             @ApiParam(name = "favoriteLecturers", type = "body", value = "Delete a favorite lecturer", required = false)
            @RequestBody List<FavoriteLecturerKey> ids);
}
