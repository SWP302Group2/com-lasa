package com.lasa.data.model.view;

import com.lasa.data.model.entity.LecturerTopicDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LecturerTopicDetailViewModel implements Serializable {
    private LecturerViewModel lecturer;
    private TopicViewModel topic;

    public LecturerTopicDetailViewModel(LecturerTopicDetail lecturerTopicDetail) {
        this.lecturer = new LecturerViewModel(lecturerTopicDetail.getLecturer());
        this.topic = new TopicViewModel(lecturerTopicDetail.getTopic());
    }
}
