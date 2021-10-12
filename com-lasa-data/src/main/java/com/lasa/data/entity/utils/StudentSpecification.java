package com.lasa.data.entity.utils;

import com.lasa.data.entity.Student;
import com.lasa.data.entity.Student_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class StudentSpecification {

    public static Specification<Student> searchSpecification(StudentSearchCriteria searchCriteria) {
        return new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.and(
                        criteriaBuilder.like(root.get(Student_.email), "%" + searchCriteria.getEmail() + "%"),
                        criteriaBuilder.like(root.get(Student_.mssv), "%" + searchCriteria.getMssv() + "%"),
                        criteriaBuilder.like(root.get(Student_.majorId), "%" + searchCriteria.getMajorId() + "%"),
                        criteriaBuilder.like(root.get(Student_.name), "%" + searchCriteria.getName() + "%"),
                        criteriaBuilder.like(root.get(Student_.phone), "%" + searchCriteria.getPhone() + "%"),
                        criteriaBuilder.like(root.get(Student_.status).as(String.class), searchCriteria.getStatus()),
                        criteriaBuilder.like(root.get(Student_.gender).as(String.class), searchCriteria.getGender())
                );
            }
        };
    }
}
