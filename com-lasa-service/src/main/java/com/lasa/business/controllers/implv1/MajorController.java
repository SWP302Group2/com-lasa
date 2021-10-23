/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.controllers.MajorOperations;
import com.lasa.business.services.MajorService;
import com.lasa.data.dto.MajorDTO;
import com.lasa.data.dto.TopicDTO;
import com.lasa.data.entity.Major;
import com.lasa.data.entity.utils.criteria.MajorSearchCriteria;
import com.lasa.data.entity.utils.page.MajorPage;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api/v1/majors")
@Api(value = "majors", description = "For majors", tags = { "Major Controller" })
public class MajorController implements MajorOperations {

    private final MajorService majorService;

    @Autowired
    public MajorController(@Qualifier("MajorServiceImplV1") MajorService majorService) {
        this.majorService = majorService;
    }


    @Override
    public ResponseEntity<?> findWithArgument(MajorPage majorPage, MajorSearchCriteria searchCriteria) {
            if(majorPage.isPaging())
                return ResponseEntity.ok(majorService.findAll(majorPage, searchCriteria));
            else
                return ResponseEntity.ok(majorService.findAll(searchCriteria));

    }

    @Override
    public ResponseEntity<MajorDTO> findById(String id) {
        /*return ResponseEntity.ok(new MajorDTO(majorService.findById(id)));*/
        return null;
    }

    @Override
    public ResponseEntity<?> findByIdIncludeTopics(String id) {
        MajorDTO majorDTO = majorService.findById(id);

        if(Objects.isNull(majorDTO))
            return ResponseEntity.ok(null);

        return ResponseEntity.ok(majorDTO);
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
