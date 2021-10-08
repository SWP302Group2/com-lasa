/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.entity.Lecturer;
import com.lasa.data.entity.LecturerPage;
import com.lasa.data.entity.LecturerSearchCriteria;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 *
 * @author hai
 */
@Service
public interface LecturerService {

    List<Lecturer> findAllLecturer();

    Lecturer createLecturer(Lecturer lecturer);

    Lecturer findLecturerById(Integer id);

    Lecturer updateLecturer(Lecturer lecturer);

    Page<Lecturer> findBasicInformationLecturers(Integer page, Integer size);
    
    Page<Lecturer> getLecturers(LecturerPage lecturerPage,
                                LecturerSearchCriteria lecturerSearchCriteria);
}
