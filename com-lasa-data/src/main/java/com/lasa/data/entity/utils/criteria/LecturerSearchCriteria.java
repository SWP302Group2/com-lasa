package com.lasa.data.entity.utils.criteria;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LecturerSearchCriteria {
    
    @ApiModelProperty(name = "email", dataType = "String", value = "Input your email")
    private String email;
    
    @ApiModelProperty(name = "name", dataType = "String", value = "Input your name")
    private String name;
    
    @ApiModelProperty(name = "phone", dataType = "String", value = "Input your phone")
    private String phone;
    
    @ApiModelProperty(name = "status", dataType = "String", value = "Get status")
    private Integer status;
    
    @ApiModelProperty(name = "gender", dataType = "Boolean", value = "Get gender")
    private Boolean gender;
    
    @ApiModelProperty(name = "address", dataType = "String", value = "Your address")
    private String address;
}
