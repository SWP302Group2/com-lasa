/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.dto.LecturerDTO;
import com.lasa.data.entity.Lecturer;
import com.lasa.data.entity.utils.criteria.LecturerSearchCriteria;
import com.lasa.data.entity.utils.page.LecturerPage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author hai
 */
@Service
public interface LecturerService {

    Page<LecturerDTO> findAll(LecturerPage lecturerPage, LecturerSearchCriteria searchCriteria);

    List<LecturerDTO> findAll(LecturerSearchCriteria searchCriteria);

    Lecturer createLecturer(Lecturer lecturer);

    Lecturer findLecturerById(Integer id);

    Lecturer updateLecturer(Lecturer lecturer);

}
