package com.lasa.data.entity.utils.criteria;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class BookingRequestSearchCriteria {
    
    @ApiModelProperty(name = "slotId", dataType = "Integer", value = "Get slot by id")
    private List<Integer> slotId;
    
    @ApiModelProperty(name = "studentId", dataType = "Integer", value = "Get student by id")
    private List<Integer> studentId;
     
    @ApiModelProperty(name = "topicId", dataType = "Integer", value = "Get topic by id")
    private List<Integer> topicId;
    
    @ApiModelProperty(name = "status", dataType = "Integer", value = "Get status")
    private Integer status;
}
