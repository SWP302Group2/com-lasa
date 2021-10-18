package com.lasa.data.entity.utils.criteria;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuestionSearchCriteria {
    @ApiModelProperty(name = "bookingId", dataType = "Integer", value = "Search question by bookingIds")
    private List<Integer> bookingId;
}