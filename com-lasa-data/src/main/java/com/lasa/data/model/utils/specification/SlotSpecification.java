package com.lasa.data.model.utils.specification;

import com.lasa.data.model.entity.*;
import com.lasa.data.model.utils.criteria.SlotSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SlotSpecification {

    public static Specification<Slot> searchSpecification(SlotSearchCriteria searchCriteria) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(Objects.nonNull(searchCriteria.getLecId()))
                predicates.add(root.get(Slot_.lecturerId).in(searchCriteria.getLecId()));

            if(Objects.nonNull(searchCriteria.getTimeStart())) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(Slot_.timeStart), searchCriteria.getTimeStart()));
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(Slot_.timeEnd), searchCriteria.getTimeStart()));
            }

            if(Objects.nonNull(searchCriteria.getTimeEnd())) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(Slot_.timeStart), searchCriteria.getTimeEnd()));
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(Slot_.timeEnd), searchCriteria.getTimeEnd()));
            }

            if(Objects.nonNull(searchCriteria.getSlotId())) {
                predicates.add(root.get(Slot_.id).in(searchCriteria.getSlotId()));
            }

            if(Objects.nonNull(searchCriteria.getTopicId())) {
                Join<Slot, SlotTopicDetail>  slotTopicJoin = root.join(Slot_.topics);
                predicates.add(slotTopicJoin.get(SlotTopicDetail_.topic).get(Topic_.id).in(searchCriteria.getTopicId()));
                query.distinct(true);
            }

            if(Objects.nonNull(searchCriteria.getStatus())) {
                predicates.add(criteriaBuilder.equal(root.get(Slot_.status), searchCriteria.getStatus()));
            }

            if(predicates.isEmpty())
                return null;
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size() - 1]));
        };
    }
}
