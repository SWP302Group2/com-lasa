/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repo.repository;

import com.lasa.data.model.entity.LecturerTopicDetail;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 *
 * @author ASUS
 */
@DataJpaTest
public class LecturerTopicDetailRepositoryTest {
    
    public LecturerTopicDetailRepositoryTest() {
    }
    @Autowired
    LecturerTopicDetailRepository topicDetailRepository;
    
    @Test
    public void testFindAllLecturerAndTopicInLecturerTopicDetail(){
        int expectedLecturerId = 1;
        int expectedTopicID = 33;
        List<LecturerTopicDetail> topicDetail = topicDetailRepository.findAllLecturerAndTopicInLecturerTopicDetail();
        Assertions.assertEquals(topicDetail.get(0).getLecturer().getId(), expectedLecturerId);
        Assertions.assertEquals(topicDetail.get(0).getTopic().getId(), expectedTopicID);  
    }
    
    @Test
    public void testFindTopicIdsByLecturerId(){     
        List<Integer> topicDetail = topicDetailRepository
                                   .findTopicIdsByLecturerId(1);     
         List<Integer> expectedTopicId = new ArrayList<>();  
         expectedTopicId.add(33);
        Assertions.assertEquals(topicDetail, expectedTopicId); 
    }
    
    @Test
    public void testFindDetailWithTopicsByLecturerId(){
        List<LecturerTopicDetail> topicDetail = topicDetailRepository
                                               .findDetailWithTopicsByLecturerId(2);     
         int expectedTopicId = 41;     
        Assertions.assertEquals(topicDetail.get(0).getTopic().getId(), expectedTopicId);      
    }
}
