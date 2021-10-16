package com.lasa.data.entity.utils.page;

import com.lasa.data.entity.Lecturer_;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
public class LecturerPage {
    private Integer page = 0;
    private Integer size = 10;
    private String sortBy = Lecturer_.ID;
    private Sort.Direction orderBy = Sort.Direction.ASC;
}
