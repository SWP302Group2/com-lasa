package com.lasa.data.entity.utils.criteria;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StudentSearchCriteria {

    @ApiModelProperty(name = "slotId", dataType = "Integer", value = "Search student by email")
    private String email;

    @ApiModelProperty(name = "mssv", dataType = "String", value = "Search student by mssv")
    private String mssv;

    @ApiModelProperty(name = "majorId", dataType = "String", value = "Search student by majorIds")
    private List<String> majorId;

    @ApiModelProperty(name = "name", dataType = "String", value = "Search student by name")
    private String name;

    @ApiModelProperty(name = "phone", dataType = "String", value = "Search student by phone")
    private String phone;

    @ApiModelProperty(name = "status", dataType = "Integer", value = "Search student by status")
    private Integer status;

    @ApiModelProperty(name = "gender", dataType = "Boolean", value = "Search student by gender")
    private Boolean gender;

    @ApiModelProperty(name = "address", dataType = "String", value = "Search student by address")
    private String address;
}
