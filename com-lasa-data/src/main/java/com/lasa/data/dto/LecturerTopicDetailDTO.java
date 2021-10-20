package com.lasa.data.dto;

import com.lasa.data.entity.Lecturer;
import com.lasa.data.entity.LecturerTopicDetail;
import com.lasa.data.entity.Topic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LecturerTopicDetailDTO implements Serializable {
    private LecturerDTO lecturer;
    private TopicDTO topic;

    public LecturerTopicDetailDTO(LecturerTopicDetail lecturerTopicDetail) {
        this.lecturer = new LecturerDTO(lecturerTopicDetail.getLecturer());
        this.topic = new TopicDTO(lecturerTopicDetail.getTopic());
    }
}
