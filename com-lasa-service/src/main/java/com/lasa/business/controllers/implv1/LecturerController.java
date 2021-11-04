/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.controllers.LecturerOperations;
import com.lasa.business.services.LecturerService;
import com.lasa.data.model.request.LecturerRequestModel;
import com.lasa.data.model.utils.criteria.LecturerSearchCriteria;
import com.lasa.data.model.utils.page.LecturerPage;
import com.lasa.data.model.view.LecturerViewModel;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<LecturerViewModel> findById(Integer id) {
        return ResponseEntity
                .ok(lecturerService.findLecturerById(id));
    }

    @Override
    public ResponseEntity<LecturerViewModel> createLecturer(LecturerRequestModel lecturer) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(lecturerService.createLecturer(lecturer));
    }

    @Override
    public ResponseEntity<LecturerViewModel>  updateLecturer(LecturerRequestModel lecturer) {
        return ResponseEntity.ok(lecturerService.updateLecturer(lecturer));
    }

    @Override
    public void deleteLecturers(List<Integer> ids) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
