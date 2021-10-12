package com.lasa.data.entity.utils;

import com.lasa.data.entity.Slot_;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
public class SlotPage {
    private Integer page = 0;
    private Integer size = 10;
    private String sortBy = Slot_.ID;
    private Sort.Direction orderBy = Sort.Direction.ASC;
}
