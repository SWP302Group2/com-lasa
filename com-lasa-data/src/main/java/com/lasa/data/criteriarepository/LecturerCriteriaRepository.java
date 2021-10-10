package com.lasa.data.criteriarepository;

import com.lasa.data.entity.Lecturer;
import com.lasa.data.entity.Lecturer_;
import com.lasa.data.entity.Slot;
import com.lasa.data.entity.Slot_;
import com.lasa.data.page.LecturerPage;
import com.lasa.data.searchcriteria.LecturerSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        /*CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();*/
        CriteriaQuery<Lecturer> criteriaQuery = criteriaBuilder.createQuery(Lecturer.class);
        Root<Lecturer> lecturerRoot = criteriaQuery.from(Lecturer.class);
        Predicate predicate = getPredicate(lecturerSearchCriteria, lecturerRoot);
        criteriaQuery.where(predicate);
        setOrder(lecturerPage, criteriaQuery, lecturerRoot);
//        CollectionJoin<Lecturer, Slot> slots = lecturerRoot.join(Lecturer_.slots, JoinType.LEFT);
        /*criteriaQuery.multiselect(
                lecturerRoot.get(Lecturer_.id),
                lecturerRoot.get(Lecturer_.email),
                lecturerRoot.get(Lecturer_.name),
                lecturerRoot.get(Lecturer_.slots)
        );*/

        System.out.println("start");
        TypedQuery<Lecturer> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(lecturerPage.getPageNumber() * lecturerPage.getPageSize());
        typedQuery.setMaxResults(lecturerPage.getPageSize());
        Pageable pageable = getPageable(lecturerPage);
        long lecturerCount = getLecturerCount(predicate);
        return new PageImpl<>(typedQuery.getResultList()/*.getResultList().stream().map(
                tuple -> {
                    return new Lecturer(
                            (Integer) tuple.get(0),
                            (String) tuple.get(1),
                            (String) tuple.get(2),
                            (Slot) tuple.get(3)
                    );
                }
        ).collect(Collectors.toList())*/, pageable, lecturerCount);
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
