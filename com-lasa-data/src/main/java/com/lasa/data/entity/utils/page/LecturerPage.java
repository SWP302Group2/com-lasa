package com.lasa.data.entity.utils.page;


import com.lasa.data.entity.Lecturer_;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
public class LecturerPage {
    @ApiModelProperty(name = "page", value = "number of page", dataType = "Integer")
    private Integer page = 0;
    
    @ApiModelProperty(name = "size", value = "number of element in page", dataType = "Integer")
    private Integer size = 10;
    
    @ApiModelProperty(
            name = "sortBy",
            value = "select a field to sort",
            dataType = "String",
            allowableValues =
                            Lecturer_.ID + "," +
                            Lecturer_.NAME + "," +
                            Lecturer_.EMAIL + "," +                           
                            Lecturer_.STATUS + ","
    )
    private String sortBy = Lecturer_.ID;
     
    @ApiModelProperty(name = "orderBy", value = "select sort direction", dataType = "Direction")
    private Sort.Direction orderBy = Sort.Direction.ASC;
    
    @ApiModelProperty(name = "paging", value = "select return a page or not", dataType = "boolean")
    private boolean paging = true;
}
