/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Admin
 */
@Repository
public class LecturerCriteriaRepository {
    
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;
    @Autowired
    public LecturerCriteriaRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Lecturer> findAllWithFilters(LecturerPage lecturerPage,
            LecturerSearchCriteria lecturerSearchCriteria) {
        CriteriaQuery<Lecturer> criteriaQuery = criteriaBuilder.createQuery(Lecturer.class);
        Root<Lecturer> lecturerRoot = criteriaQuery.from(Lecturer.class);
        Predicate predicate = getPredicate(lecturerSearchCriteria, lecturerRoot);
        criteriaQuery.where(predicate);
        setOrder(lecturerPage, criteriaQuery, lecturerRoot);

        TypedQuery<Lecturer> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(lecturerPage.getPageNum() * lecturerPage.getPageSize());
        typedQuery.setMaxResults(lecturerPage.getPageSize());

        Pageable pageable = getPageable(lecturerPage);

        long lecturerCount = getLecturerCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, lecturerCount);
    }

    private Predicate getPredicate(LecturerSearchCriteria lecturerSearchCriteria,
            Root<Lecturer> lecturerRoot) {
        List<Predicate> predicates = new ArrayList<>();
        String pattern = "%" + lecturerSearchCriteria.getName() + "%";
        if (Objects.nonNull(lecturerSearchCriteria.getName())) {
            predicates.add(criteriaBuilder.like(lecturerRoot.get("name"), pattern));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(LecturerPage lecturerPage,
            CriteriaQuery<Lecturer> criteriaQuery,
            Root<Lecturer> lecturerRoot) {
        if (lecturerPage.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(lecturerRoot.get(lecturerPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(lecturerRoot.get(lecturerPage.getSortBy())));
        }
    }

    private Pageable getPageable(LecturerPage lecturerPage) {
        Sort sort = Sort.by(lecturerPage.getSortDirection(), lecturerPage.getSortBy());
        return PageRequest.of(lecturerPage.getPageNum(), lecturerPage.getPageSize(), sort);
    }

    private long getLecturerCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Lecturer> countRoot = countQuery.from(Lecturer.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
