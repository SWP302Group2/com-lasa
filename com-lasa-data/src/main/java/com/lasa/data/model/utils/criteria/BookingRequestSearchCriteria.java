package com.lasa.data.model.utils.criteria;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class BookingRequestSearchCriteria {
    
    @ApiModelProperty(name = "slotId", dataType = "Integer", value = "Search booking request by  slotIds")
    private List<Integer> slotId;
    
    @ApiModelProperty(name = "studentId", dataType = "Integer", value = "Search booking request by studentIds")
    private List<Integer> studentId;
     
    @ApiModelProperty(name = "topicId", dataType = "Integer", value = "Search booking request by topicIds")
    private List<Integer> topicId;
    
    @ApiModelProperty(name = "status", dataType = "Integer", value = "Search booking request by status")
    private Integer status;
}
