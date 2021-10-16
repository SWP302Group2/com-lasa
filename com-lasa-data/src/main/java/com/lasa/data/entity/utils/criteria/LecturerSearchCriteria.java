package com.lasa.data.entity.utils.criteria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LecturerSearchCriteria {
    private String email;
    private String name;
    private String phone;
    private Integer status;
    private Boolean gender;
    private String address;
}
