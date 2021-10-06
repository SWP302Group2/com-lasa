/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.servicesv1;

import com.lasa.data.entity.LecturerTopicDetail;
import com.lasa.data.entity.key.LecturerTopicDetailKey;
import com.lasa.data.repository.LecturerTopicDetailRepository;
import java.util.List;

import com.lasa.business.services.LecturerTopicDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component
@Qualifier("LecturerTopicDetailImplV1")
public class LecturerTopicDetailImplV1 implements LecturerTopicDetailService {

    private final LecturerTopicDetailRepository lecturerTopicDetailRepository;

    @Autowired
    public LecturerTopicDetailImplV1(LecturerTopicDetailRepository lecturerTopicDetailRepository) {
        this.lecturerTopicDetailRepository = lecturerTopicDetailRepository;
    }

    @Override
    public List<LecturerTopicDetail> findAllLecturerAndTopicInLecturerTopicDetail() {
        return lecturerTopicDetailRepository.findAllLecturerAndTopicInLecturerTopicDetail();
    }

    @Override
    public LecturerTopicDetail findById(LecturerTopicDetailKey id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LecturerTopicDetail> createLecturerTopicDetails(List<LecturerTopicDetail> details) {
        return lecturerTopicDetailRepository.saveAll(details);
    }

    @Override
    public List<LecturerTopicDetail> updateLecturerTopicDetails(List<LecturerTopicDetail> details) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteLecturerTopicDetails(List<LecturerTopicDetailKey> ids) {
        lecturerTopicDetailRepository.deleteAllById(ids);
    }

}
