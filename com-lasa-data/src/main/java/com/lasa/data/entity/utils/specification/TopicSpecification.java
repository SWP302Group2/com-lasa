package com.lasa.data.entity.utils.specification;

import com.lasa.data.entity.Topic;
import com.lasa.data.entity.Topic_;
import com.lasa.data.entity.utils.criteria.TopicSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TopicSpecification {

    public static Specification<Topic> searchSpecification(TopicSearchCriteria searchCriteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(Objects.nonNull(searchCriteria.getCourseId()))
                predicates.add(criteriaBuilder.like(root.get(Topic_.courseId), "%" + searchCriteria.getCourseId() + "%"));

            if(Objects.nonNull(searchCriteria.getName()))
                predicates.add(criteriaBuilder.like(root.get(Topic_.name), "%" + searchCriteria.getName() + "%"));

            if(Objects.nonNull(searchCriteria.getStatus()))
                predicates.add(criteriaBuilder.equal(root.get(Topic_.status), searchCriteria.getStatus()));

            if(Objects.nonNull(searchCriteria.getMajorId()))
                predicates.add(criteriaBuilder.isTrue(root.get(Topic_.majorId).in(searchCriteria.getMajorId())));

            if(predicates.isEmpty())
                return null;
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size() - 1]));
        };
    }


}
