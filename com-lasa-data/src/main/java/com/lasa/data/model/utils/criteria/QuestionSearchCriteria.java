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
public class QuestionSearchCriteria {
    @ApiModelProperty(name = "bookingId", dataType = "Integer", value = "Search question by bookingIds")
    private List<Integer> bookingId;

    @Builder
    public QuestionSearchCriteria(List<Integer> bookingId) {
        this.bookingId = bookingId;
    }
}
