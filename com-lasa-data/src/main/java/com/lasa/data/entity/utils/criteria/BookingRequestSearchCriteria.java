package com.lasa.data.entity.utils.criteria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookingRequestSearchCriteria {
    private List<Integer> slotId;
    private List<Integer> studentId;
    private List<Integer> topicId;
    private Integer status;
}
