package com.lasa.data.entity.utils.specification;

import com.lasa.data.entity.SlotTopicDetail;
import com.lasa.data.entity.SlotTopicDetail_;
import com.lasa.data.entity.Slot_;
import com.lasa.data.entity.Topic_;
import com.lasa.data.entity.utils.criteria.SlotTopicDetailSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SlotTopicDetailSpecification {

    public static Specification<SlotTopicDetail> searchSpecification(SlotTopicDetailSearchCriteria searchCriteria) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(Objects.nonNull(searchCriteria.getSId()))
                predicates.add(criteriaBuilder.isTrue(root.get(SlotTopicDetail_.slot).get(Slot_.id).in(searchCriteria.getSId())));

            if(Objects.nonNull(searchCriteria.getTopicId()))
                predicates.add(criteriaBuilder.isTrue(root.get(SlotTopicDetail_.topic).get(Topic_.id).in(searchCriteria.getTopicId())));

            if(predicates.isEmpty())
                return null;
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size() - 1]));
        };
    }
}
