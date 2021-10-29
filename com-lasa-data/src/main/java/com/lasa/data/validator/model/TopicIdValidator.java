package com.lasa.data.validator.model;

import com.lasa.data.model.entity.key.SlotTopicDetailKey;
import com.lasa.data.model.request.BookingRequestRequestModel;
import com.lasa.data.repo.repository.SlotTopicDetailRepository;
import com.lasa.data.validator.ValidTopicId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TopicIdValidator implements ConstraintValidator<ValidTopicId, BookingRequestRequestModel> {

    private final SlotTopicDetailRepository slotTopicDetailRepository;

    @Autowired
    public TopicIdValidator(SlotTopicDetailRepository slotTopicDetailRepository) {
        this.slotTopicDetailRepository = slotTopicDetailRepository;
    }

    @Override
    public boolean isValid(BookingRequestRequestModel bookingRequestRequestModel, ConstraintValidatorContext constraintValidatorContext) {

        return slotTopicDetailRepository.existsById(SlotTopicDetailKey.builder()
                        .slotId(bookingRequestRequestModel.getSlotId())
                        .topicId(bookingRequestRequestModel.getTopicId())
                .build());
    }
}
