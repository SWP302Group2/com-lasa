package com.lasa.data.entity.utils.criteria;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StudentSearchCriteria {

    @ApiModelProperty(name = "slotId", dataType = "Integer", value = "Get slot by id")
    private String email;

    @ApiModelProperty(name = "mssv", dataType = "String", value = "Get studentId")
    private String mssv;

    @ApiModelProperty(name = "majorId", dataType = "String", value = "Get major by id")
    private List<String> majorId;

    @ApiModelProperty(name = "name", dataType = "String", value = "Get student name")
    private String name;

    @ApiModelProperty(name = "phone", dataType = "String", value = "Input your phone")
    private String phone;

    @ApiModelProperty(name = "status", dataType = "Integer", value = "Get status")
    private Integer status;

    @ApiModelProperty(name = "gender", dataType = "Boolean", value = "Get gender")
    private Boolean gender;

    @ApiModelProperty(name = "address", dataType = "String", value = "Input your address")
    private String address;
}
