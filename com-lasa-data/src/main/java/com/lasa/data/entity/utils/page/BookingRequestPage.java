package com.lasa.data.entity.utils.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
public class BookingRequestPage {

    private static final String SORT_BY_ALLOW_VALUES =
            BookingRequest_.ID + ","
            + BookingRequest_.STUDENT_ID + ","
            + BookingRequest_.TOPIC_ID + ","
            + BookingRequest_.SLOT_ID + ","
            + BookingRequest_.STATUS;

    @ApiModelProperty(name = "page", value = "number of page", dataType = "Integer")
    private Integer page = 0;

    @ApiModelProperty(name = "size", value = "number of element in page", dataType = "Integer")
    private Integer size = 10;

    @ApiModelProperty(
            name = "sortBy",
            value = "select a field to sort",
            dataType = "String",
            allowableValues = SORT_BY_ALLOW_VALUES)
    private String sortBy = BookingRequest_.ID;

    @ApiModelProperty(name = "orderBy", value = "select sort direction", dataType = "Direction")
    private Sort.Direction orderBy = Sort.Direction.ASC;
    @ApiModelProperty(name = "paging", value = "select return a page or not", dataType = "boolean")
    private boolean paging = true;

}