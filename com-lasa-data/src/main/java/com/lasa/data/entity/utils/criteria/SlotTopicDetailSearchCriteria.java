package com.lasa.data.entity.utils.criteria;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SlotTopicDetailSearchCriteria {
    
    @ApiModelProperty(name = "sId", dataType = "Integer", value = "Search slot topic detail by slotIds")
    private List<Integer> sId;
    
    @ApiModelProperty(name = "topicId", dataType = "Integer", value = "Search slot topic detail by topicIds")
    private List<Integer> topicId;
}
