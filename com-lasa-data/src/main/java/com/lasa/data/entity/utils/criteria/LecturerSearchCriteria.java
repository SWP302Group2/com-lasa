package com.lasa.data.entity.utils.criteria;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class LecturerSearchCriteria {
    
    @ApiModelProperty(name = "email", dataType = "String", value = "Search lecturer by email")
    private String email;
    
    @ApiModelProperty(name = "name", dataType = "String", value = "Search lecturer by name")
    private String name;
    
    @ApiModelProperty(name = "phone", dataType = "String", value = "Search lecturer by phone")
    private String phone;
    
    @ApiModelProperty(name = "status", dataType = "Integer", value = "Search lecturer by status")
    private Integer status;
    
    @ApiModelProperty(name = "gender", dataType = "Boolean", value = "Search lecturer by gender")
    private Boolean gender;
    
    @ApiModelProperty(name = "address", dataType = "String", value = "Search lecturer by address")
    private String address;

}
