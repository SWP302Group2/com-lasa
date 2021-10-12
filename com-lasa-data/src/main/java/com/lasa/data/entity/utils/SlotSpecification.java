package com.lasa.data.entity.utils;

import com.lasa.data.entity.*;
import com.lasa.data.entity.utils.SlotSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class SlotSpecification {

    public static Specification<Slot> searchSpecification(SlotSearchCriteria searchCriteria) {
        return new Specification<Slot>() {
            @Override
            public Predicate toPredicate(Root<Slot> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                return criteriaBuilder.and(
                        criteriaBuilder.like(root.get("lecturerId").as(String.class), searchCriteria.getLecturerId()),
                        criteriaBuilder.between(root.get(Slot_.timeStart), searchCriteria.getTimeStart(), searchCriteria.getTimeEnd()),
                        criteriaBuilder.between(root.get(Slot_.timeEnd), searchCriteria.getTimeStart(), searchCriteria.getTimeEnd())
                );
            }
        };
    }
}
