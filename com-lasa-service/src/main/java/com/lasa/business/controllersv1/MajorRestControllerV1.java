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

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.*;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api/v1/majors")
@Api(value = "majors", description = "For majors", tags = { "Major Controller" })
public class MajorRestControllerV1 implements MajorOperations {

    private final MajorService service;

    @Autowired
    public MajorRestControllerV1(@Qualifier("MajorServiceImplV1") MajorService service) {
        this.service = service;
    }

    @Override
    public List<Major> getAllMajor() {
        return service.findAll();
    }

    @Override
    public Major getMajorById(String id) {
        return service.findById(id);
    }

    @Override
    public void createMajors(List<Major> majors) {
        service.createMajors(majors);
    }

    @Override
    public List<Major> updateMajors(List<Major> majors) {
        return service.updateMajors(majors);
    }

    @Override
    public void deleteMajors(List<String> ids) {
        service.deleteMajors(ids);
    }

}
