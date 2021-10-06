/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.FavoriteLecturer;
import com.lasa.data.entity.key.FavoriteLecturerKey;
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
    List<FavoriteLecturer> createFavoriteLecturer(@RequestBody List<FavoriteLecturer> favoriteLecturers);
    
    @DeleteMapping
    void deleteFavoriteLecturers(@RequestBody List<FavoriteLecturerKey> ids);
}
