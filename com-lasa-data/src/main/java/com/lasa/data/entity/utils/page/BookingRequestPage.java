package com.lasa.data.entity.utils.page;

import com.lasa.data.entity.BookingRequest_;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
public class BookingRequestPage {
    private Integer page = 0;
    private Integer size = 10;
    private String sortBy = BookingRequest_.ID;
    private Sort.Direction orderBy = Sort.Direction.ASC;
    private boolean paging = true;

}
