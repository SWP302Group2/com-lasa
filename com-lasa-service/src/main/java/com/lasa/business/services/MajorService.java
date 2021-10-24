/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.model.request.MajorRequestModel;
import com.lasa.data.model.view.MajorViewModel;
import com.lasa.data.model.utils.criteria.MajorSearchCriteria;
import com.lasa.data.model.utils.page.MajorPage;
import com.lasa.data.model.utils.projection.MajorWithSimpleTopic;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author hai
 */
@Service
public interface MajorService {
    
    Page<MajorViewModel> findAll(MajorPage majorPage, MajorSearchCriteria searchCriteria);

    List<MajorViewModel> findAll(MajorSearchCriteria searchCriteria);

    List<MajorWithSimpleTopic> findAllWithTopicIds();
    
    MajorViewModel findById(String id);
    
    List<MajorViewModel> createMajors(List<MajorRequestModel> majors);
    
    List<MajorViewModel> updateMajors(List<MajorRequestModel> majors);
    
    void deleteMajors(List<String> ids);
}
