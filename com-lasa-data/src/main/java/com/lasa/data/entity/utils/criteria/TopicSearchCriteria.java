package com.lasa.data.entity.utils.criteria;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class TopicSearchCriteria {
    
    @ApiModelProperty(name = "name", dataType = "String", value = "Get topic name")
    private String name;
    
    @ApiModelProperty(name = "courseId", dataType = "String", value = "Get course id")
    private String courseId;
    
    @ApiModelProperty(name = "majorId", dataType = "String", value = "Get major by id")
    private List<String> majorId;
     
    @ApiModelProperty(name = "status", dataType = "Integer", value = "Get status") 
    private Integer status;
}
