package com.lasa.data.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.lasa.data.entity.BookingRequest;
import com.lasa.data.entity.LecturerTopicDetail;
import com.lasa.data.entity.SlotTopicDetail;
import com.lasa.data.entity.Topic;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id", scope = TopicDTO.class)
public class TopicDTO implements Serializable {
    private Integer id;
    private String name;
    private String majorId;
    private String courseId;
    private Integer status;
    private String description;

    public TopicDTO(Topic topic) {
        this.id = topic.getId();
        this.name = topic.getName();
        this.majorId = topic.getMajorId();
        this.courseId = topic.getCourseId();
        this.status = topic.getStatus();
        this.description = topic.getDescription();
    }
}
