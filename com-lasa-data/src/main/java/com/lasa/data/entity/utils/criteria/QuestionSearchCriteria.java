package com.lasa.data.entity.utils.criteria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuestionSearchCriteria {
    private List<Integer> bookingId;
}
