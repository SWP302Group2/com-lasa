/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.model.request;

import com.lasa.data.model.entity.Topic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author ASUS
 */
@SpringBootTest
public class TopicRequestModelTest {

    @Test
    public void toEntityTestGivenRightArgumentReturnWells() {
        TopicRequestModel model = new TopicRequestModel();
        Integer id = 1;
        String name = "Introduction";
        String majorId = "SE";
        String courseId = "CSI104";
        Integer status = 1;
        String description = "Introduction Course";

        model.setId(id);
        model.setName(name);
        model.setMajorId(majorId);
        model.setCourseId(courseId);
        model.setStatus(status);
        model.setDescription(description);

        Topic topic = model.toEntity();

        Assertions.assertEquals(topic.getId(), id);
        Assertions.assertEquals(topic.getName(), name);
        Assertions.assertEquals(topic.getMajorId(), majorId);
        Assertions.assertEquals(topic.getCourseId(), courseId);
        Assertions.assertEquals(topic.getStatus(), status);
        Assertions.assertEquals(topic.getDescription(), description);

    }

}
