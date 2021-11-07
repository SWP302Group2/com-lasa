package com.lasa.data.model.utils.criteria;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
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

    @ApiModelProperty(name = "getTopicAndSlot", dataType = "Boolean", value = "Get all information of topic and slot")
    private Boolean getTopicAndSlot = false;

    @Builder
    public SlotTopicDetailSearchCriteria(List<Integer> sId, List<Integer> topicId, Boolean getTopicAndSlot) {
        this.sId = sId;
        this.topicId = topicId;
        this.getTopicAndSlot = getTopicAndSlot;
    }
}
