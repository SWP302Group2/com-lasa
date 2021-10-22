package com.lasa.data.entity.utils.criteria;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
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

    private List<Integer> lecId;

    @Builder
    public LecturerSearchCriteria(String email, String name, String phone, Integer status, Boolean gender, String address, List<Integer> lecId) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.gender = gender;
        this.address = address;
        this.lecId = lecId;
    }
}
