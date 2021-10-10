package com.lasa.data.entity.predicate;

import com.lasa.data.entity.Lecturer;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LecturerSpecificationBuilder {

    private final List<SearchCriteria> params;

    public LecturerSpecificationBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public LecturerSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Lecturer> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(searchCriteria -> {
                    return LecturerSpecifications.SearchSpecification(searchCriteria);
                })
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result).and(specs.get(i));

        }
        return result;
    }
}
