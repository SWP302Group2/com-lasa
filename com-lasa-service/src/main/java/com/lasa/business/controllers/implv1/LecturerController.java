/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.controllers.LecturerOperations;
import com.lasa.business.services.LecturerService;
import com.lasa.data.dto.LecturerDTO;
import com.lasa.data.entity.Lecturer;
import com.lasa.data.entity.utils.criteria.LecturerSearchCriteria;
import com.lasa.data.entity.utils.page.LecturerPage;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author hai
 */
@RestController
@RequestMapping("api/v1/lecturers")
@Api(value = "lecturers", description = "About lecturers", tags = { "Lecturers Controller" })
public class LecturerController implements LecturerOperations {
    
    private final LecturerService lecturerService;

    @Autowired
    public LecturerController(@Qualifier("LecturerServiceImplV1") LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @Override
    public ResponseEntity<?> findWithArgument(LecturerPage lecturerPage, LecturerSearchCriteria searchCriteria) {
            if(lecturerPage.isPaging())
                return ResponseEntity.ok(lecturerService.findAll(lecturerPage, searchCriteria));
            else
                return ResponseEntity.ok(lecturerService.findAll(searchCriteria));
    }

    @Override
    public ResponseEntity<LecturerDTO> findById(Integer id) {
        return ResponseEntity
                .ok(new LecturerDTO(lecturerService.findLecturerById(id)));
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
