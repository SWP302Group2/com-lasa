package com.lasa.data.model.request;

import com.lasa.data.model.entity.Topic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicRequestModel {
    private Integer id;
    private String name;
    private String majorId;
    private String courseId;
    private Integer status;
    private String description;

    public Topic toEntity() {
        return Topic.builder()
                .id(id)
                .name(name)
                .majorId(majorId)
                .courseId(courseId)
                .status(status)
                .description(description)
                .build();
    }
}
