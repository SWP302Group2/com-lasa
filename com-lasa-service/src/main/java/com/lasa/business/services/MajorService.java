/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.entity.Major;
import java.util.List;

import com.lasa.data.entity.utils.criteria.MajorSearchCriteria;
import com.lasa.data.entity.utils.page.MajorPage;
import com.lasa.data.entity.utils.projection.MajorSimpleTopic;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 *
 * @author hai
 */
@Service
public interface MajorService {
    
    Page<Major> findAll(MajorPage majorPage, MajorSearchCriteria searchCriteria);

    List<Major> findAll(MajorSearchCriteria searchCriteria);

    List<MajorSimpleTopic> findAllWithTopicIds();
    
    Major findById(String id);
    
    List<Major> createMajors(List<Major> majors);
    
    List<Major> updateMajors(List<Major> majors);
    
    void deleteMajors(List<String> ids);
}
