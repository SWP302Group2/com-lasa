package com.lasa.data.validator.model;

import com.lasa.data.model.request.SlotRequestModel;
import com.lasa.data.repo.repository.SlotTopicDetailRepository;
import com.lasa.data.repo.repository.TopicRepository;
import com.lasa.data.validator.ValidTopicUpdate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class TopicUpdateValidator implements ConstraintValidator<ValidTopicUpdate, SlotRequestModel> {

    private final TopicRepository topicRepository;

    public TopicUpdateValidator(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public boolean isValid(SlotRequestModel slotRequestModel, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.isNull(slotRequestModel.getTopics()))
            return true;

        return topicRepository.countAvailableTopics(slotRequestModel.getTopics()) == slotRequestModel.getTopics().size();
    }
}
