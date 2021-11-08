/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.config.utils.StudentStatus;
import com.lasa.business.controllers.StudentOperations;
import com.lasa.business.controllers.utils.authorization.IsAdminOrStudent;
import com.lasa.business.controllers.utils.authorization.IsNotAvailable;
import com.lasa.business.services.StudentService;
import com.lasa.data.model.request.StudentRequestModel;
import com.lasa.data.model.utils.criteria.StudentSearchCriteria;
import com.lasa.data.model.utils.page.StudentPage;
import com.lasa.data.model.view.StudentViewModel;
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
 * @author Admin
 */
@RestController
@RequestMapping("/api/v1/students")
@Api(value = "students", description = "About students", tags = { "Students Controller" })
public class StudentController implements StudentOperations {

    private final StudentService service;

    @Autowired
    public StudentController(@Qualifier("StudentServiceImplV1") StudentService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<?> findWithArguments(StudentSearchCriteria searchCriteria, StudentPage studentPage) {
        if(studentPage.isPaging())
            return ResponseEntity.ok(service.findWithArgument(searchCriteria, studentPage));
        else
            return ResponseEntity.ok(service.findWithArgument(searchCriteria));
    }

    @Override
    public ResponseEntity<StudentViewModel> findByStudentId(Integer id) {
        return ResponseEntity.ok(service.findByStudentId(id));
    }

    @Override
    @IsNotAvailable
    public ResponseEntity<StudentViewModel> createStudent(StudentRequestModel student) {
        return ResponseEntity.ok(service.createStudent(student));
    }

    @Override
    @IsAdminOrStudent
    @PreAuthorize("(hasAuthority(ROLE_ADMIN)) or (#student.id.equals(authentication.principle.id))")
    public ResponseEntity<StudentViewModel> updateStudent(StudentRequestModel student) throws ExceptionUtils.UpdateException {
        if(Objects.nonNull(student.getStatus())) {
            if(student.getStatus() != StudentStatus.ACTIVATED.getCode()
                    && !SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains("ROLE_ADMIN"))
                throw new ExceptionUtils.UpdateException("STUDENT_STATUS_NOT_AVAILABLE_FOR_UPDATE");
        }

        return ResponseEntity.ok(service.updateStudent(student));
    }

    @Override
    @IsAdminOrStudent
    @PreAuthorize("(hasAuthority(ROLE_ADMIN)) or ((#id.size() == 1) and (#id.get(0).equals(authentication.principal.id)))")
    public ResponseEntity<?> deleteStudents(List<Integer> id) throws ExceptionUtils.DeleteException {
        if(!service.verifyStudent(id))
            throw new ExceptionUtils.DeleteException("STUDENT_NOT_AVAILABLE_FOR_DELETE");

        service.deleteStudents(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
