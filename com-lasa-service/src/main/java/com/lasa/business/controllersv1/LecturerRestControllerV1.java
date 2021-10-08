/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllersv1;

import com.lasa.business.controllers.LecturerOperations;
import com.lasa.data.entity.Lecturer;
import com.lasa.business.services.LecturerService;
import java.util.List;

import com.lasa.data.page.LecturerPage;
import com.lasa.data.searchcriteria.LecturerSearchCriteria;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.*;

/**
 *
 * @author hai
 */
@RestController
@RequestMapping("api/v1/lecturers")
@Api(value = "lecturers", description = "About lecturers", tags = { "Lecturers Controller" })
public class LecturerRestControllerV1 implements LecturerOperations {
    
    private final LecturerService lecturerService;

    @Autowired
    public LecturerRestControllerV1(@Qualifier("LecturerServiceImplV1") LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @Override
    public List<Lecturer> findAll() {
        return lecturerService.findAllLecturer();
    }

    @Override
    public ResponseEntity<?> findBasicInformationLecturers(Integer page, Integer size) {
        return ResponseEntity.ok(lecturerService.findBasicInformationLecturers(page, size));
    }

    @Override
    public ResponseEntity<Page<Lecturer>> getLecturer(LecturerPage lecturerPage, LecturerSearchCriteria lecturerSearchCriteria) {
        return new ResponseEntity<>(lecturerService.getLecturers(lecturerPage, lecturerSearchCriteria), HttpStatus.OK);
    }

    @Override
    public Lecturer findByLecturerId(Integer id) {
        return lecturerService.findLecturerById(id);
    }

    @Override
    public Lecturer createLecturer(Lecturer lecturer) {
        return lecturerService.createLecturer(lecturer);
    }

    @Override
    public Lecturer updateLecturer(Lecturer lecturer) {
        return lecturerService.updateLecturer(lecturer);
    }

    @Override
    public void deleteLecturers(List<Integer> ids) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
