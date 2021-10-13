/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.FavoriteLecturer;
import com.lasa.data.entity.key.FavoriteLecturerKey;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author hai
 */
@RequestMapping("/default")
public interface FavoriteLecturerOperations {
    
    @GetMapping
    List<FavoriteLecturer> findAll();

    @PostMapping
    List<FavoriteLecturer> createFavoriteLecturer(
            @ApiParam(name = "favoriteLecturers", type = "body", value = "Create a favorite lecturer", required = true)
            @RequestBody List<FavoriteLecturer> favoriteLecturers);
    
    @DeleteMapping
    void deleteFavoriteLecturers(
             @ApiParam(name = "favoriteLecturers", type = "body", value = "Delete a favorite lecturer", required = false)
            @RequestBody List<FavoriteLecturerKey> ids);
}
