package com.lasa.data.model.utils.specification;

import com.lasa.data.model.entity.SlotTopicDetail;
import com.lasa.data.model.entity.SlotTopicDetail_;
import com.lasa.data.model.entity.Slot_;
import com.lasa.data.model.entity.Topic_;
import com.lasa.data.model.utils.criteria.SlotTopicDetailSearchCriteria;
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
                predicates.add(root.get(SlotTopicDetail_.slot).get(Slot_.id).in(searchCriteria.getSId()));

            if(Objects.nonNull(searchCriteria.getTopicId()))
                predicates.add(root.get(SlotTopicDetail_.topic).get(Topic_.id).in(searchCriteria.getTopicId()));

          /*  if(searchCriteria.getGetTopicAndSlot().equals(true)) {
                Fetch<SlotTopicDetail, Topic> detailTopicFetch = root.fetch(SlotTopicDetail_.topic);
                Fetch<SlotTopicDetail, Slot> detailSlotFetch = root.fetch(SlotTopicDetail_.slot);
                query.distinct(true);
            }*/


            if(predicates.isEmpty())
                return null;
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size() - 1]));
        };
    }
}
