package com.lasa.data.model.view;

import com.lasa.data.model.entity.Major;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MajorViewModel implements Serializable {
    private String id;
    private String name;
    private String description;
    private Collection<TopicViewModel> topics = new ArrayList<>();

    public MajorViewModel(Major major) {
        this.id = major.getId();
        this.name = major.getName();
        this.description = major.getDescription();
    }


    public void addTopic(TopicViewModel dto) {
        topics.add(dto);
    }
}
