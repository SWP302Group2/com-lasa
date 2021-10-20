package com.lasa.data.entity.utils.specification;

import com.lasa.data.entity.*;
import com.lasa.data.entity.utils.criteria.SlotSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

            if(searchCriteria.getGetTopic().equals(true)) {
                Fetch<Slot, SlotTopicDetail> topicDetailFetch = root.fetch(Slot_.topics);
                Fetch<SlotTopicDetail, Topic> detailTopicFetch = topicDetailFetch.fetch(SlotTopicDetail_.topic);
            }

            if(predicates.isEmpty())
                return null;
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size() - 1]));
        };
    }
}
