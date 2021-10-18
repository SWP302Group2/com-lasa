/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.entity.FavoriteLecturer;
import com.lasa.data.entity.Lecturer;
import com.lasa.data.entity.key.FavoriteLecturerKey;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author hai
 */
@Service
public interface FavoriteLecturerService {
    
    List<FavoriteLecturer> findAllLecturerAndStudentInFavoriteLecturer();

    List<Lecturer> findTopFavoriteLecturer(Integer topNumber);
    
    List<FavoriteLecturer> addFavoriteLecturers(List<FavoriteLecturer> favoriteLecturers);

    void deleteFavoriteLecturers(List<FavoriteLecturerKey> ids);
    
}
