package com.lasa.data.validator.model;

import com.lasa.data.model.request.SlotRequestModel;
import com.lasa.data.repo.repository.SlotTopicDetailRepository;
import com.lasa.data.validator.ValidTopicUpdate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TopicUpdateValidator implements ConstraintValidator<ValidTopicUpdate, SlotRequestModel> {

    private final SlotTopicDetailRepository slotTopicDetailRepository;

    public TopicUpdateValidator(SlotTopicDetailRepository slotTopicDetailRepository) {
        this.slotTopicDetailRepository = slotTopicDetailRepository;
    }

    @Override
    public boolean isValid(SlotRequestModel slotRequestModel, ConstraintValidatorContext constraintValidatorContext) {
        return slotTopicDetailRepository.countByTopicIdInAndSlotId(slotRequestModel.getTopics(), slotRequestModel.getId()) == 0;
    }
}
