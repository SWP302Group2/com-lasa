package com.lasa.data.criteriarepository;

import com.lasa.data.entity.Lecturer;
import com.lasa.data.page.LecturerPage;
import com.lasa.data.searchcriteria.LecturerSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class LecturerCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    @Autowired
    public LecturerCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Lecturer> fillAllWithFilter(LecturerPage lecturerPage,
                                            LecturerSearchCriteria lecturerSearchCriteria) {
        CriteriaQuery<Lecturer> criteriaQuery = criteriaBuilder.createQuery(Lecturer.class);
        Root<Lecturer> lecturerRoot = criteriaQuery.from(Lecturer.class);
        Predicate predicate = getPredicate(lecturerSearchCriteria, lecturerRoot);
        criteriaQuery.where(predicate);
        setOrder(lecturerPage, criteriaQuery, lecturerRoot);

        TypedQuery<Lecturer> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(lecturerPage.getPageNumber() * lecturerPage.getPageSize());
        typedQuery.setMaxResults(lecturerPage.getPageSize());

        Pageable pageable = getPageable(lecturerPage);
        long lecturerCount = getLecturerCount(predicate);
        return new PageImpl<>(typedQuery.getResultList(), pageable, lecturerCount);
    }

    private long getLecturerCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Lecturer> countRoot = countQuery.from(Lecturer.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private Pageable getPageable(LecturerPage lecturerPage) {
        Sort sort = Sort.by(lecturerPage.getSortDirection(), lecturerPage.getSortBy());
        return PageRequest.of(lecturerPage.getPageNumber(), lecturerPage.getPageSize(), sort);
    }

    private void setOrder(LecturerPage lecturerPage,
                          CriteriaQuery<Lecturer> criteriaQuery,
                          Root<Lecturer> lecturerRoot) {
        if(lecturerPage.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(lecturerRoot.get(lecturerPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc((lecturerRoot.get(lecturerPage.getSortBy()))));
        }
    }

    private Predicate getPredicate(LecturerSearchCriteria lecturerSearchCriteria,
                                   Root<Lecturer> lecturerRoot) {
        List<Predicate> predicates = new ArrayList<>();
        String pattern = "%" + lecturerSearchCriteria.getName() + "%";
        if(Objects.nonNull(lecturerSearchCriteria.getName())) {
            predicates.add(criteriaBuilder.like(lecturerRoot.get("name"), pattern)) ;
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
