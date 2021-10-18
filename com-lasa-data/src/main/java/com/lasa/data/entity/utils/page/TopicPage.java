package com.lasa.data.entity.utils.page;

import com.lasa.data.entity.Topic_;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
public class TopicPage {
    private Integer page = 0;
    private Integer size = 10;
    private String sortBy = Topic_.ID;
    private Sort.Direction orderBy = Sort.Direction.ASC;
    private boolean paging = true;
}