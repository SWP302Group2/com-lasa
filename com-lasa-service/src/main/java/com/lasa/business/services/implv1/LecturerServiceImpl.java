/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services.implv1;

import com.lasa.business.services.LecturerService;
import com.lasa.data.entity.Lecturer;
import com.lasa.data.entity.utils.criteria.LecturerSearchCriteria;
import com.lasa.data.entity.utils.page.LecturerPage;
import com.lasa.data.entity.utils.specification.LecturerSpecification;
import com.lasa.data.repo.repository.FavoriteLecturerRepository;
import com.lasa.data.repo.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author hai
 */

@Component
@Qualifier("LecturerServiceImplV1")
public class LecturerServiceImpl implements LecturerService {

    private final LecturerRepository lecturerRepository;
    private final FavoriteLecturerRepository favoriteLecturerRepository;

    @Autowired
    public LecturerServiceImpl(LecturerRepository lecturerRepository,
                               FavoriteLecturerRepository favoriteLecturerRepository) {
        this.lecturerRepository = lecturerRepository;
        this.favoriteLecturerRepository = favoriteLecturerRepository;
    }

    @Override
    public Page<Lecturer> findAll(LecturerPage lecturerPage, LecturerSearchCriteria searchCriteria) {
        Pageable pageable = PageRequest.of(lecturerPage.getPage(), lecturerPage.getSize(), Sort.by(lecturerPage.getOrderBy(), lecturerPage.getSortBy()));

        if(Objects.nonNull(searchCriteria.getTop())) {
            List<Integer> lecturerIds = favoriteLecturerRepository.findTopFavoriteLecturerId(searchCriteria.getTop());
            searchCriteria.setLecturerIds(lecturerIds);
        }
        return lecturerRepository.findAll(LecturerSpecification.searchSpecification(searchCriteria), pageable);
    }

    @Override
    public List<Lecturer> findAll(LecturerSearchCriteria searchCriteria) {
        return lecturerRepository.findAll(LecturerSpecification.searchSpecification(searchCriteria));
    }

    @Override
    public Lecturer createLecturer(Lecturer lecturer) {
        return lecturerRepository.save(lecturer);
    }

    @Override
    public Lecturer findLecturerById(Integer id) {
        return lecturerRepository.findById(id).orElse(null);
    }

    @Override
    public Lecturer updateLecturer(Lecturer updateLecturer) {
        
        Lecturer lecturer = lecturerRepository.findById(updateLecturer.getId()).get();
        
        if(updateLecturer.getName() != null)
            lecturer.setName(updateLecturer.getName());
        
        if(updateLecturer.getPhone() != null)
            lecturer.setPhone(updateLecturer.getPhone());
        
        if(updateLecturer.getBirthday() != null)
            lecturer.setBirthday(updateLecturer.getBirthday());
        
        if(updateLecturer.getGender() != null)
            lecturer.setGender(updateLecturer.getGender());
        
        if(updateLecturer.getAddress() != null)
            lecturer.setAddress(updateLecturer.getAddress());
        
        if(updateLecturer.getMeetingUrl() != null)
            lecturer.setMeetingUrl(updateLecturer.getMeetingUrl());
        
        return lecturerRepository.save(lecturer);

    }

}
