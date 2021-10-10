package com.lasa.data.entity.predicate;

import com.lasa.data.entity.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Root;

public class LecturerSpecifications implements Specification<Lecturer> {

    @Override
    public Predicate toPredicate(javax.persistence.criteria.Root<Lecturer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        /*if(Long.class != query.getResultType()) {
            root.fetch(Lecturer_.slots, JoinType.LEFT);
        }*/


        /*query.multiselect(
                root.get(Lecturer_.id),
                root.get(Lecturer_.name)
        );*/
        /*if(Long.class != query.getResultType()) {
            Fetch<Lecturer, LecturerTopicDetail> details = root.fetch(Lecturer_.topics, JoinType.LEFT);
            Fetch<LecturerTopicDetail, Topic> topic = details.fetch(LecturerTopicDetail_.topic JoinType.LEFT);
        }

        if(Long.class != query.getResultType()) {
            root.fetch(Lecturer_.students, JoinType.LEFT);
        }*/

        return query.multiselect(
                root.get(Lecturer_.id),
                root.get(Lecturer_.name)
        ).getRestriction();
    }

    public static Specification<Lecturer> SearchSpecification(SearchCriteria searchCriteria) {
        return new Specification<Lecturer>() {
            @Override
            public Predicate toPredicate(javax.persistence.criteria.Root<Lecturer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                if(searchCriteria.getOperation().equalsIgnoreCase(":")) {
                    if(root.get(searchCriteria.getKey()).getJavaType() == String.class) {
                        return criteriaBuilder.like(
                                root.get(searchCriteria.getKey()), "%" + searchCriteria.getValue() + "%"
                        );
                    } else {
                        return criteriaBuilder.equal(
                                root.get(searchCriteria.getKey()), searchCriteria.getValue()
                        );
                    }
                }

                return null;
            }
        };
    }
}