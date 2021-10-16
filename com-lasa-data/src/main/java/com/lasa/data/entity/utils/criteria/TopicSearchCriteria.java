package com.lasa.data.entity.utils.criteria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class TopicSearchCriteria {
    private String name;
    private String courseId;
    private List<String> majorId;
    private Integer status;
}
