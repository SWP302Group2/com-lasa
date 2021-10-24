package com.lasa.data.model.view;

import com.lasa.data.model.entity.Major;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MajorViewModel implements Serializable {
    private String id;
    private String name;
    private String description;
    private Collection<TopicViewModel> topics;

    public MajorViewModel(Major major) {
        this.id = major.getId();
        this.name = major.getName();
        this.description = major.getDescription();
    }


    public void addTopic(TopicViewModel dto) {
        topics.add(dto);
    }
}
