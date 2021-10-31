/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.model.request.FavoriteLecturerRequestModel;
import com.lasa.data.model.view.FavoriteLecturerViewModel;
import com.lasa.data.model.view.LecturerViewModel;
import com.lasa.data.model.entity.FavoriteLecturer;
import com.lasa.data.model.entity.key.FavoriteLecturerKey;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

/**
 *
 * @author hai
 */
@Service
public interface FavoriteLecturerService {
    
    List<FavoriteLecturerViewModel> findAllLecturerAndStudentInFavoriteLecturer();

    List<LecturerViewModel> findTopFavoriteLecturer(Integer topNumber);
    
    List<FavoriteLecturerViewModel> addFavoriteLecturers(List<FavoriteLecturerRequestModel> favoriteLecturers);

    void deleteFavoriteLecturers(List<FavoriteLecturerKey> ids);
    
}
