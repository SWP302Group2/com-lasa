package com.lasa.data.model.utils.criteria;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class BookingRequestSearchCriteria {
    
    @ApiModelProperty(name = "slotId", dataType = "Integer", value = "Search booking request by  slotIds")
    private List<@Min(1) Integer> slotId;
    
    @ApiModelProperty(name = "studentId", dataType = "Integer", value = "Search booking request by studentIds")
    private List<@Min(1) Integer> studentId;
     
    @ApiModelProperty(name = "topicId", dataType = "Integer", value = "Search booking request by topicIds")
    private List<@Min(1) Integer> topicId;
    
    @ApiModelProperty(name = "status", dataType = "Integer", value = "Search booking request by status")
    @Min(-1)
    @Max(4)
    private Integer status;

    @ApiModelProperty(name = "getStudent", dataType = "Boolean", value = "Get information of student make this booking request")
    private Boolean getStudent = false;

    @Builder
    public BookingRequestSearchCriteria(List<@Min(1) Integer> slotId, List<@Min(1) Integer> studentId, List<@Min(1) Integer> topicId, Integer status, Boolean getStudent) {
        this.slotId = slotId;
        this.studentId = studentId;
        this.topicId = topicId;
        this.status = status;
        this.getStudent = getStudent;
    }
}
