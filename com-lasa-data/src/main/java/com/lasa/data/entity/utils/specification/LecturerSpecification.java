package com.lasa.data.entity.utils.specification;

import com.lasa.data.entity.Lecturer;
import com.lasa.data.entity.Lecturer_;
import com.lasa.data.entity.utils.criteria.LecturerSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LecturerSpecification {

    public static Specification<Lecturer> searchSpecification(LecturerSearchCriteria searchCriteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(Objects.nonNull(searchCriteria.getEmail()))
                predicates.add(criteriaBuilder.like(root.get(Lecturer_.email), "%" + searchCriteria.getEmail() + "%"));

            if(Objects.nonNull(searchCriteria.getName()))
                predicates.add(criteriaBuilder.like(root.get(Lecturer_.name), "%" + searchCriteria.getName() + "%"));

            if(Objects.nonNull(searchCriteria.getGender()))
                predicates.add(criteriaBuilder.equal(root.get(Lecturer_.gender), searchCriteria.getGender()));

            if(Objects.nonNull(searchCriteria.getPhone()))
                predicates.add(criteriaBuilder.like(root.get(Lecturer_.phone), "%" + searchCriteria.getPhone() + "%"));

            if(Objects.nonNull(searchCriteria.getAddress()))
                predicates.add(criteriaBuilder.like(root.get(Lecturer_.address), "%" + searchCriteria.getAddress() + "%"));

            if(Objects.nonNull(searchCriteria.getStatus()))
                predicates.add(criteriaBuilder.equal(root.get(Lecturer_.status), searchCriteria.getStatus()));

            if(predicates.isEmpty())
                return null;
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size() - 1]));
        };
    }
}
