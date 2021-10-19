package com.lasa.data.entity.utils.specification;

import com.lasa.data.entity.Major;
import com.lasa.data.entity.Major_;
import com.lasa.data.entity.utils.criteria.MajorSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MajorSpecification {

    public static Specification<Major> searchSpecification(MajorSearchCriteria searchCriteria) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(Objects.nonNull(searchCriteria.getName()))
                predicates.add(criteriaBuilder.like(root.get(Major_.name), "%" + searchCriteria.getName() + "%"));

            if(predicates.isEmpty())
                return null;
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size() - 1]));
        };
    }
}
