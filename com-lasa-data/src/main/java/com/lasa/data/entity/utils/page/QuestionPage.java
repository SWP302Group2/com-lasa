package com.lasa.data.entity.utils.page;

import com.lasa.data.entity.Question_;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
public class QuestionPage {
    private Integer page = 0;
    private Integer size = 10;
    private String sortBy = Question_.ID;
    private Sort.Direction orderBy = Sort.Direction.ASC;
}
