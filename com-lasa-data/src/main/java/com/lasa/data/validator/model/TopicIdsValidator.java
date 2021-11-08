package com.lasa.data.validator.model;

import com.lasa.data.repo.repository.TopicRepository;
import com.lasa.data.validator.ValidTopicIds;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class TopicIdsValidator implements ConstraintValidator<ValidTopicIds, List<Integer>> {

    private final TopicRepository topicRepository;

    public TopicIdsValidator(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public boolean isValid(List<Integer> ids, ConstraintValidatorContext constraintValidatorContext) {
        return topicRepository.countAvailableTopics(ids) == ids.size();
    }
}
