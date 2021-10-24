package com.lasa.data.model.request;

import com.lasa.data.model.entity.Lecturer;
import com.lasa.data.model.entity.LecturerTopicDetail;
import com.lasa.data.model.entity.Topic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LecturerTopicDetailRequestModel {
    private Integer lecturerId;
    private Integer topicId;

    public LecturerTopicDetail toEntity() {
        return LecturerTopicDetail.builder()
                .lecturer(Lecturer.builder()
                        .id(lecturerId)
                        .build())
                .topic(Topic.builder()
                        .id(topicId)
                        .build())
                .build();
    }

    public LecturerTopicDetail toEntity(Integer lecturerId, Integer topicId) {
        this.lecturerId = lecturerId;
        this.topicId = topicId;
        return toEntity();
    }
}
