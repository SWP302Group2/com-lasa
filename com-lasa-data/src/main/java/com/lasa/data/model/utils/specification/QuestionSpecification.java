package com.lasa.data.model.utils.specification;

import com.lasa.data.model.entity.BookingRequest_;
import com.lasa.data.model.entity.Question;
import com.lasa.data.model.entity.Question_;
import com.lasa.data.model.utils.criteria.QuestionSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuestionSpecification {

    public static Specification<Question> searchSpecification(QuestionSearchCriteria searchCriteria) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(Objects.nonNull(searchCriteria.getBookingId()))
                predicates.add(root.get(Question_.bookingRequest).get(BookingRequest_.id).in(searchCriteria.getBookingId()));

            if(predicates.isEmpty())
                return null;
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size() - 1]));
        };
    }
}
