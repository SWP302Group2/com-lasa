package com.lasa.data.validator.model;

import com.lasa.data.model.entity.key.SlotTopicDetailKey;
import com.lasa.data.model.request.BookingRequestRequestModel;
import com.lasa.data.repo.repository.SlotTopicDetailRepository;
import com.lasa.data.validator.ValidBookingTopicId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class TopicIdValidator implements ConstraintValidator<ValidBookingTopicId, BookingRequestRequestModel> {

    private final SlotTopicDetailRepository slotTopicDetailRepository;

    @Autowired
    public TopicIdValidator(SlotTopicDetailRepository slotTopicDetailRepository) {
        this.slotTopicDetailRepository = slotTopicDetailRepository;
    }

    @Override
    public boolean isValid(BookingRequestRequestModel bookingRequestRequestModel, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.nonNull(bookingRequestRequestModel.getId()) && Objects.nonNull(bookingRequestRequestModel.getTopicId())) {
            if(bookingRequestRequestModel.getTopicId() <= 0 || bookingRequestRequestModel.getSlotId() <= 0)
                return false;

            return slotTopicDetailRepository.existsById(SlotTopicDetailKey.builder()
                    .slotId(bookingRequestRequestModel.getSlotId())
                    .topicId(bookingRequestRequestModel.getTopicId())
                    .build());
        }

        return true;
    }
}
