package com.lasa.data.dto;

import com.lasa.data.entity.Major;
import com.lasa.data.entity.Student;
import com.lasa.data.entity.Topic;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MajorDTO implements Serializable {
    private String id;
    private String name;
    private String description;
    private Collection<TopicDTO> topics;

    public MajorDTO(Major major) {
        this.id = major.getId();
        this.name = major.getName();
        this.description = major.getDescription();
    }


    public void addTopic(TopicDTO dto) {
        topics.add(dto);
    }
}