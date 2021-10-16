/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllersv1;

import com.lasa.business.controllers.MajorOperations;
import com.lasa.data.entity.Major;
import com.lasa.business.services.MajorService;
import java.util.List;

import com.lasa.data.entity.utils.criteria.MajorSearchCriteria;
import com.lasa.data.entity.utils.page.MajorPage;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api/v1/majors")
@Api(value = "majors", description = "For majors", tags = { "Major Controller" })
public class MajorRestControllerV1 implements MajorOperations {

    private final MajorService majorService;

    @Autowired
    public MajorRestControllerV1(@Qualifier("MajorServiceImplV1") MajorService majorService) {
        this.majorService = majorService;
    }


    @Override
    public ResponseEntity<?> findAll(MajorPage majorPage, MajorSearchCriteria searchCriteria) {
        return ResponseEntity.ok(majorService.findAll(majorPage, searchCriteria));
    }

    @Override
    public Major findById(String id) {
        return majorService.findById(id);
    }

    @Override
    public void createMajors(List<Major> majors) {
        majorService.createMajors(majors);
    }

    @Override
    public List<Major> updateMajors(List<Major> majors) {
        return majorService.updateMajors(majors);
    }

    @Override
    public void deleteMajors(List<String> ids) {
        majorService.deleteMajors(ids);
    }

}
