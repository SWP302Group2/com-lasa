package com.lasa.data.validator.model;

import com.lasa.data.model.request.SlotRequestModel;
import com.lasa.data.repo.repository.LecturerTopicDetailRepository;
import com.lasa.data.repo.repository.TopicRepository;
import com.lasa.data.validator.ValidSlotTopicForCreateSlot;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class SlotTopicForCreateSlotValidator implements ConstraintValidator<ValidSlotTopicForCreateSlot, SlotRequestModel> {

    private final TopicRepository topicRepository;
    private final LecturerTopicDetailRepository lecturerTopicDetailRepository;

    @Autowired
    public SlotTopicForCreateSlotValidator(TopicRepository topicRepository, LecturerTopicDetailRepository lecturerTopicDetailRepository) {
        this.topicRepository = topicRepository;
        this.lecturerTopicDetailRepository = lecturerTopicDetailRepository;
    }

    @Override
    public boolean isValid(SlotRequestModel slotRequestModel, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.nonNull(slotRequestModel.getTopics())) {
            if(!slotRequestModel.getTopics().isEmpty()) {
                return topicRepository.countByIdIn(slotRequestModel.getTopics()) == slotRequestModel.getTopics().size();
            }
        }
        if(Objects.nonNull(slotRequestModel.getLecturerId())) {
            return lecturerTopicDetailRepository.countByLecturerId(slotRequestModel.getLecturerId()) > 0;
        }

        return false;

    }
}
