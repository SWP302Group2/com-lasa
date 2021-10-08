/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllersv1;

import com.lasa.business.controllers.LecturerTopicDetailOperations;
import com.lasa.data.entity.LecturerTopicDetail;
import com.lasa.data.entity.key.LecturerTopicDetailKey;
import com.lasa.business.services.LecturerTopicDetailService;
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
 * @author hai
 */
@RestController
@RequestMapping("api/v1/lecturer-topic-details")
@Api(value = "lecturer-topic-details", description = "About topic details for lecturer", tags = { "Lecturer topic details" })
public class LecturerTopicDetailRestController implements LecturerTopicDetailOperations {
    
    private final LecturerTopicDetailService lecturerTopicDetailService;

    @Autowired
    public LecturerTopicDetailRestController(@Qualifier("LecturerTopicDetailImplV1") LecturerTopicDetailService lecturerTopicDetailService) {
        this.lecturerTopicDetailService = lecturerTopicDetailService;
    }

    @Override
    public List<LecturerTopicDetail> findAll() {
        return lecturerTopicDetailService.findAllLecturerAndTopicInLecturerTopicDetail();
    }

    @Override
    public List<LecturerTopicDetail> createLecturerTopicDetails(List<LecturerTopicDetail> lecturerTopicDetails) {
        return lecturerTopicDetailService.createLecturerTopicDetails(lecturerTopicDetails);
    }

    @Override
    public void deleteLecturerTopicDetails(List<LecturerTopicDetailKey> ids) {
        lecturerTopicDetailService.deleteLecturerTopicDetails(ids);
    }
    
    
}
