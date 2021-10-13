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
        return new Specification<Slot>() {
            @Override
            public Predicate toPredicate(Root<Slot> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();

                if(Objects.nonNull(searchCriteria.getLecturerId()))
                    predicates.add(criteriaBuilder.equal(root.get(Slot_.lecturer), searchCriteria.getLecturerId()));

                if(Objects.nonNull(searchCriteria.getLecturerName())) {
                    Join<Slot, Lecturer> lecturerJoin = root.join(Slot_.lecturer);
                    predicates.add(criteriaBuilder.like(lecturerJoin.get(Lecturer_.name), "%" + searchCriteria.getLecturerName() + "%"));
                }

                if(Objects.nonNull(searchCriteria.getTimeStart())) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.<LocalDateTime>get(Slot_.timeStart), searchCriteria.getTimeStart()));
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.<LocalDateTime>get(Slot_.timeEnd), searchCriteria.getTimeStart()));
                }

                if(Objects.nonNull(searchCriteria.getTimeEnd())) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.<LocalDateTime>get(Slot_.timeStart), searchCriteria.getTimeEnd()));
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.<LocalDateTime>get(Slot_.timeEnd), searchCriteria.getTimeEnd()));
                }

                if(Objects.nonNull(searchCriteria.getTopicId()) || Objects.nonNull(searchCriteria.getTopicName())) {
                    Join<Slot, SlotTopicDetail> slotTopicDetailJoin = root.join(Slot_.topics, JoinType.LEFT);

                    if(Objects.nonNull(searchCriteria.getLecturerId()))
                        predicates.add(criteriaBuilder.equal(slotTopicDetailJoin.get(SlotTopicDetail_.topic).get(Topic_.id), searchCriteria.getTopicId()));

                    if(Objects.nonNull(searchCriteria.getTopicName())) {
                        Join<SlotTopicDetail, Topic> topicJoin = slotTopicDetailJoin.join(SlotTopicDetail_.topic, JoinType.LEFT);
                        predicates.add(criteriaBuilder.like(topicJoin.get(Topic_.name), "%" + searchCriteria.getTopicName() + "%"));
                    }

                }

                if(predicates.isEmpty())
                    return null;
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size() - 1]));
            }
        };
    }
}
