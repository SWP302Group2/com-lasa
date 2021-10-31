package com.lasa.data.model.request;

import com.lasa.data.validator.ValidBookingQuestionCreate;
import com.lasa.data.validator.ValidBookingQuestionUpdate;
import com.lasa.data.validator.group.PostValidator;
import com.lasa.data.validator.group.PutValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ValidBookingQuestionCreate(groups = PostValidator.class, message = "QUESTION_NOT_VALID_OR_QUESTIONS_OVERFLOW")
@ValidBookingQuestionUpdate(groups = PutValidator.class, message = "QUESTION_NOT_FOUND_OR_NOT_AVAILABLE")
public class BookingQuestionRequestModel {
    private Integer id;
    private Integer studentId;
    private List<@Valid QuestionRequestModel> questions;
}
