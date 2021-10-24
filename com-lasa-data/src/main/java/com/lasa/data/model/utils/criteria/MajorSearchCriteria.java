package com.lasa.data.model.utils.criteria;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MajorSearchCriteria {
    @ApiModelProperty(name = "name", dataType = "String", value = "Search major by name")
    private String name;
    private Boolean getTopic;
}
