package com.lasa.data.entity.utils.criteria;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MajorSearchCriteria {
    @ApiModelProperty(name = "name", dataType = "String", value = "Input your major")
    private String name;
}
