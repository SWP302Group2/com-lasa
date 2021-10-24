package com.lasa.data.model.view;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.lasa.data.model.entity.Topic;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id", scope = TopicViewModel.class)
public class TopicViewModel implements Serializable {
    private Integer id;
    private String name;
    private String majorId;
    private String courseId;
    private Integer status;
    private String description;

    public TopicViewModel(Topic topic) {
        this.id = topic.getId();
        this.name = topic.getName();
        this.majorId = topic.getMajorId();
        this.courseId = topic.getCourseId();
        this.status = topic.getStatus();
        this.description = topic.getDescription();
    }

    @Builder
    public TopicViewModel(Integer id, String name, String majorId, String courseId, Integer status, String description) {
        this.id = id;
        this.name = name;
        this.majorId = majorId;
        this.courseId = courseId;
        this.status = status;
        this.description = description;
    }
}
