/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.config.utils.LecturerStatus;
import com.lasa.business.controllers.LecturerOperations;
import com.lasa.business.controllers.utils.authorization.IsAdminOrLecturer;
import com.lasa.business.controllers.utils.authorization.IsNotAvailable;
import com.lasa.business.services.LecturerService;
import com.lasa.data.model.request.LecturerRequestModel;
import com.lasa.data.model.utils.criteria.LecturerSearchCriteria;
import com.lasa.data.model.utils.page.LecturerPage;
import com.lasa.data.model.view.LecturerViewModel;
import com.lasa.security.utils.exception.ExceptionUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<LecturerViewModel> findById(Integer id) {
        return ResponseEntity
                .ok(lecturerService.findLecturerById(id));
    }

    @Override
    @IsNotAvailable
    public ResponseEntity<LecturerViewModel> createLecturer(LecturerRequestModel lecturer) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(lecturerService.createLecturer(lecturer));
    }

    @Override
    @IsAdminOrLecturer
    @PreAuthorize("#lecturer.id.equals(authentication.principal.id) or hasAuthority('ROLE_ADMIN') ")
    public ResponseEntity<LecturerViewModel>  updateLecturer(LecturerRequestModel lecturer) throws ExceptionUtils.UpdateException {
        if(Objects.nonNull(lecturer.getStatus())) {
            if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains("ROLE_ADMIN"))
                throw new ExceptionUtils.UpdateException("LECTURER_STATUS_NOT_AVAILABLE_FOR_UPDATE");
        }

        return ResponseEntity.ok(lecturerService.updateLecturer(lecturer));
    }

    @Override
    @IsAdminOrLecturer
    @PreAuthorize("(hasAuthority(ROLE_ADMIN)) or ((#id.size() == 1) and (#id.get(0).equals(authentication.principal.id)))")
    public ResponseEntity<?> deleteLecturers(List<Integer> id) throws ExceptionUtils.DeleteException {
        if(!lecturerService.verifyLecturer(id))
            throw new ExceptionUtils.DeleteException("LECTURER_NOT_AVAILABLE_FOR_DELETE");

        lecturerService.deleteLecturer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
