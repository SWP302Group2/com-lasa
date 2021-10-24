package com.lasa.data.model.utils.page;

import com.lasa.data.model.entity.Question_;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
public class QuestionPage {
    
    @ApiModelProperty(name = "page", value = "number of page", dataType = "Integer")
    private Integer page = 0;
    
    @ApiModelProperty(name = "size", value = "number of element in page", dataType = "Integer")
    private Integer size = 10;
    
     @ApiModelProperty(
            name = "sortBy",
            value = "select a field to sort",
            dataType = "String",
            allowableValues =
                            Question_.ID + "," +
                            Question_.BOOKING_REQUEST + "," +
                            Question_.CONTENT 
    )
    private String sortBy = Question_.ID;
     
    @ApiModelProperty(name = "orderBy", value = "select sort direction", dataType = "Direction")
    private Sort.Direction orderBy = Sort.Direction.ASC;
    
    @ApiModelProperty(name = "paging", value = "select return a page or not", dataType = "boolean")
    private boolean paging = true;
}
