/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.servicesv1;

import com.lasa.data.entity.Lecturer;
import com.lasa.data.repository.LecturerRepository;
import java.util.List;

import com.lasa.business.services.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 *
 * @author hai
 */

@Component
@Qualifier("LecturerServiceImplV1")
public class LecturerServiceImplV1 implements LecturerService {

    private final LecturerRepository lecturerRepository;

    @Autowired
    public LecturerServiceImplV1(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public List<Lecturer> findAllLecturer() {
        return lecturerRepository.findAll();
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

    @Override
    public Page<Lecturer> findBasicInformationLecturers(Integer page, Integer size) {
        return lecturerRepository.findBasicInformationLecturers(PageRequest.of(page, size));
    }

}
