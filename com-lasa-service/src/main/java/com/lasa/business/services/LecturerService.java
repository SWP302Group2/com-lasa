/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.model.request.LecturerRequestModel;
import com.lasa.data.model.utils.criteria.LecturerSearchCriteria;
import com.lasa.data.model.utils.page.LecturerPage;
import com.lasa.data.model.view.LecturerViewModel;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author hai
 */
@Service
public interface LecturerService {

    Page<LecturerViewModel> findAll(LecturerPage lecturerPage, LecturerSearchCriteria searchCriteria);

    List<LecturerViewModel> findAll(LecturerSearchCriteria searchCriteria);

    LecturerViewModel createLecturer(LecturerRequestModel model);

    LecturerViewModel findLecturerById(Integer id);

    LecturerViewModel updateLecturer(LecturerRequestModel lecturer);

}
