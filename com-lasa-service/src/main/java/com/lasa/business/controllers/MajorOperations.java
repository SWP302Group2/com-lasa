/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.model.request.MajorRequestModel;
import com.lasa.data.model.utils.criteria.MajorSearchCriteria;
import com.lasa.data.model.utils.page.MajorPage;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Admin
 */
@RequestMapping("/default")
public interface MajorOperations {

    @GetMapping
    ResponseEntity<?> findWithArgument(MajorPage majorPage,
                                       MajorSearchCriteria searchCriteria);

    @GetMapping(value = "/{id}")
    ResponseEntity<?> findById(@ApiParam(name = "id", type = "String", value = "Get Major by id", required = false)
                               @PathVariable(value = "id", required = false) String id);

    @GetMapping(value = "/{id}/topics")
    ResponseEntity<?> findByIdIncludeTopics(@ApiParam(name = "id", type = "String", value = "Get Major by id", required = false)
                                            @PathVariable(value = "id", required = false) String id);

    @PostMapping
    ResponseEntity<?> createMajors(@ApiParam(name = "majors", type = "body", value = "Add a major")
                                   @RequestBody List<MajorRequestModel> majors);

    @PatchMapping
    ResponseEntity<?> updateMajors(@ApiParam(name = "majors", type = "body", value = "Update a major")
                             @RequestBody List<MajorRequestModel> majors);

    @DeleteMapping
    void deleteMajors(
            @ApiParam(name = "ids", type = "body", value = "Delete a major by id", defaultValue = "false")
            @RequestBody List<String> ids);
}
