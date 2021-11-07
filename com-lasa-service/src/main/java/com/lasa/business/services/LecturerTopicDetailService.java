/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.model.entity.LecturerTopicDetail;
import com.lasa.data.model.entity.key.LecturerTopicDetailKey;
import com.lasa.data.model.view.LecturerTopicDetailViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Admin
 */
@Service
public interface LecturerTopicDetailService {

    List<LecturerTopicDetailViewModel> findAllLecturerAndTopicInLecturerTopicDetail();

    List<Integer> findListTopicIdByLecturerId(Integer lecturerId);

    LecturerTopicDetail findById(LecturerTopicDetailKey id);

    List<LecturerTopicDetail> createLecturerTopicDetails(List<LecturerTopicDetail> details);

    List<LecturerTopicDetail> updateLecturerTopicDetails(List<LecturerTopicDetail> details);

    void deleteLecturerTopicDetails(List<LecturerTopicDetailKey> key);
}
