package com.lasa.data.model.utils.criteria;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class TopicSearchCriteria {
    
    @ApiModelProperty(name = "name", dataType = "String", value = "Search topic by name")
    private String name;
    
    @ApiModelProperty(name = "courseId", dataType = "String", value = "Search topic by course id")
    private String courseId;
    
    @ApiModelProperty(name = "majorId", dataType = "String", value = "Search topic by majorIds")
    private List<String> majorId;
     
    @ApiModelProperty(name = "status", dataType = "Integer", value = "Search topic by status")
    private Integer status;
}
