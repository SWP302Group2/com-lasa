package com.lasa.data.entity.utils;

import com.lasa.data.entity.*;
import com.lasa.data.entity.utils.SlotSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SlotSpecification {

    public static Specification<Slot> searchSpecification(SlotSearchCriteria searchCriteria) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(searchCriteria.getLecturerId()))
                predicates.add(criteriaBuilder.isTrue(root.get(Slot_.lecturerId).in(searchCriteria.getLecturerId())));

            if (Objects.nonNull(searchCriteria.getTimeStart())) {
                predicates.add(
                        criteriaBuilder.greaterThanOrEqualTo(root.get(Slot_.timeStart), searchCriteria.getTimeStart()));
                predicates.add(
                        criteriaBuilder.greaterThanOrEqualTo(root.get(Slot_.timeEnd), searchCriteria.getTimeStart()));
            }

            if (Objects.nonNull(searchCriteria.getTimeEnd())) {
                predicates
                        .add(criteriaBuilder.lessThanOrEqualTo(root.get(Slot_.timeStart), searchCriteria.getTimeEnd()));
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(Slot_.timeEnd), searchCriteria.getTimeEnd()));
            }

            if (predicates.isEmpty())
                return null;
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size() - 1]));
        };
    }
}
