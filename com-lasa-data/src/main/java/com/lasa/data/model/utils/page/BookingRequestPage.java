package com.lasa.data.model.utils.page;

import com.lasa.data.model.entity.BookingRequest_;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
public class BookingRequestPage {

    @ApiModelProperty(name = "page", value = "number of page", dataType = "Integer")
    @Min(0)
    private Integer page = 0;

    @ApiModelProperty(name = "size", value = "number of element in page", dataType = "Integer")
    @Min(0)
    private Integer size = 10;

    @ApiModelProperty(
            name = "sortBy",
            value = "select a field to sort",
            dataType = "String",
            allowableValues =
                            BookingRequest_.ID + "," +
                            BookingRequest_.STATUS + "," +
                            BookingRequest_.SLOT_ID + "," +
                            BookingRequest_.TOPIC_ID + "," +
                            BookingRequest_.STUDENT_ID + ","
    )
    private String sortBy = BookingRequest_.ID;

    @ApiModelProperty(name = "orderBy", value = "select sort direction", dataType = "Direction")
    private Sort.Direction orderBy = Sort.Direction.ASC;
    @ApiModelProperty(name = "paging", value = "select return a page or not", dataType = "boolean")
    private boolean paging = true;
}
