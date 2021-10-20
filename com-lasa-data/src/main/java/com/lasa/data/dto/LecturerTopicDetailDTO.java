package com.lasa.data.dto;

import com.lasa.data.entity.Lecturer;
import com.lasa.data.entity.LecturerTopicDetail;
import com.lasa.data.entity.Topic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LecturerTopicDetailDTO {
    private Lecturer lecturer;
    private Topic topic;

    public LecturerTopicDetailDTO(LecturerTopicDetail lecturerTopicDetail) {
        this.lecturer = lecturerTopicDetail.getLecturer();
        this.topic = lecturerTopicDetail.getTopic();
    }
}
