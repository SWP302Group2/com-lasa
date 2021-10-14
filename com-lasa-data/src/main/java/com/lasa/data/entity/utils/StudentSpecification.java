package com.lasa.data.entity.utils;

import com.lasa.data.entity.Student;
import com.lasa.data.entity.Student_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentSpecification {

    public static Specification<Student> searchSpecification(StudentSearchCriteria searchCriteria) {
        return new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();

                if(Objects.nonNull(searchCriteria.getEmail()))
                    predicates.add(criteriaBuilder.like(root.get(Student_.email), "%" + searchCriteria.getEmail() + "%"));

                if(Objects.nonNull(searchCriteria.getMssv()))
                    predicates.add(criteriaBuilder.like(root.get(Student_.mssv), "%" + searchCriteria.getMssv() + "%"));

                if(Objects.nonNull(searchCriteria.getStatus()))
                    predicates.add(criteriaBuilder.equal(root.get(Student_.status), searchCriteria.getStatus()));

                if(Objects.nonNull(searchCriteria.getMajorId()))
                    predicates.add(criteriaBuilder.isTrue(root.get(Student_.majorId).in(searchCriteria.getMajorId())));

                if(Objects.nonNull(searchCriteria.getName()))
                    predicates.add(criteriaBuilder.like(root.get(Student_.name), "%" + searchCriteria.getName() + "%"));

                if(Objects.nonNull(searchCriteria.getPhone()))
                    predicates.add(criteriaBuilder.like(root.get(Student_.phone), "%" + searchCriteria.getPhone() + "%"));

                if(Objects.nonNull(searchCriteria.getGender()))
                    predicates.add(criteriaBuilder.equal(root.get(Student_.gender), searchCriteria.getGender()));

                if(predicates.isEmpty())
                    return null;
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size() - 1]));
            }
        };
    }
}
