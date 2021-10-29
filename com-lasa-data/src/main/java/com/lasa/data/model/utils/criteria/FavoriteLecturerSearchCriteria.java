package com.lasa.data.model.utils.criteria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;

@NoArgsConstructor
@Getter
@Setter
public class FavoriteLecturerSearchCriteria {
    @Min(1)
    private Integer topLecturer;
}
