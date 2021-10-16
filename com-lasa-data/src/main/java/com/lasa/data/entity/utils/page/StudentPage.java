package com.lasa.data.entity.utils.page;

import com.lasa.data.entity.Slot_;
import com.lasa.data.entity.Student_;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
public class StudentPage {
    private Integer page = 0;
    private Integer size = 10;
    private String sortBy = Student_.ID;
    private Sort.Direction orderBy = Sort.Direction.ASC;
}
