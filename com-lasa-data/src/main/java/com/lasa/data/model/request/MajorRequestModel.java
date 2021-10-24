package com.lasa.data.model.request;

import com.lasa.data.model.entity.Major;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MajorRequestModel {
    private String id;
    private String name;
    private String description;
    private Collection<Integer> topics;

    public Major toEntity() {
        return Major.builder()
                .id(id)
                .name(name)
                .description(description)
                .build();
    }
}
