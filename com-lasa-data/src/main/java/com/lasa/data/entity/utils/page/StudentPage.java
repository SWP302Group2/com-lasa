package com.lasa.data.entity.utils.page;

import com.lasa.data.entity.Slot_;
import com.lasa.data.entity.Student_;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
public class StudentPage {
    @ApiModelProperty(name = "page", value = "number of page", dataType = "Integer")
    private Integer page = 0;
    
    @ApiModelProperty(name = "size", value = "number of element in page", dataType = "Integer")
    private Integer size = 10;
     @ApiModelProperty(
            name = "sortBy",
            value = "select a field to sort",
            dataType = "String",
            allowableValues =
                            Student_.ID + "," +
                            Student_.MAJOR_ID + "," +
                            Student_.MSSV + "," +  
                            Student_.EMAIL + "," + 
                            Student_.STATUS + ","
    )
    private String sortBy = Student_.ID;
     
    @ApiModelProperty(name = "orderBy", value = "select sort direction", dataType = "Direction")
    private Sort.Direction orderBy = Sort.Direction.ASC;
     
    @ApiModelProperty(name = "paging", value = "select return a page or not", dataType = "boolean")
    private boolean paging = true;
}
