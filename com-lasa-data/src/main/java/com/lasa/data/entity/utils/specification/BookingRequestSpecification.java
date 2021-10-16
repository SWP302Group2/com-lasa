package com.lasa.data.entity.utils.specification;

import com.lasa.data.entity.BookingRequest;
import com.lasa.data.entity.BookingRequest_;
import com.lasa.data.entity.utils.criteria.BookingRequestSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookingRequestSpecification {

    public static Specification<BookingRequest> searchSpecification(BookingRequestSearchCriteria searchCriteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(Objects.nonNull(searchCriteria.getStatus()))
                predicates.add(criteriaBuilder.equal(root.get(BookingRequest_.status), searchCriteria.getStatus()));

            if(Objects.nonNull(searchCriteria.getSlotId()))
                predicates.add(criteriaBuilder.isTrue(root.get(BookingRequest_.slotId).in(searchCriteria.getSlotId())));

            if(Objects.nonNull(searchCriteria.getStudentId()))
                predicates.add(criteriaBuilder.isTrue(root.get(BookingRequest_.studentId).in(searchCriteria.getStudentId())));

            if(Objects.nonNull(searchCriteria.getTopicId()))
                predicates.add(criteriaBuilder.isTrue(root.get(BookingRequest_.topicId).in(searchCriteria.getStudentId())));

            if(predicates.isEmpty())
                return null;
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size() - 1]));
        };
    }
}
