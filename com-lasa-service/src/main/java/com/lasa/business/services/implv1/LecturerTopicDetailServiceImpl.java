/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services.implv1;

import com.lasa.business.services.LecturerTopicDetailService;
import com.lasa.data.model.entity.Topic;
import com.lasa.data.model.view.LecturerTopicDetailViewModel;
import com.lasa.data.model.entity.LecturerTopicDetail;
import com.lasa.data.model.entity.key.LecturerTopicDetailKey;
import com.lasa.data.model.view.TopicViewModel;
import com.lasa.data.repo.repository.LecturerTopicDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Admin
 */
@Component
@Qualifier("LecturerTopicDetailServiceImplV1")
public class LecturerTopicDetailServiceImpl implements LecturerTopicDetailService {

    private final LecturerTopicDetailRepository lecturerTopicDetailRepository;

    @Autowired
    public LecturerTopicDetailServiceImpl(LecturerTopicDetailRepository lecturerTopicDetailRepository) {
        this.lecturerTopicDetailRepository = lecturerTopicDetailRepository;
    }

    @Override
    public List<LecturerTopicDetailViewModel> findAllLecturerAndTopicInLecturerTopicDetail() {
        return lecturerTopicDetailRepository.
                findAllLecturerAndTopicInLecturerTopicDetail()
                .stream()
                .map(t -> new LecturerTopicDetailViewModel(t))
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> findListTopicIdByLecturerId(Integer lecturerId) {
        return lecturerTopicDetailRepository.findTopicIdsByLecturerId(lecturerId);
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
