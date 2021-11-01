package com.lasa.data.model.request;

import com.lasa.data.validator.ValidBookingQuestionDelete;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ValidBookingQuestionDelete(message = "QUESTIONS_NOT_AVAILABLE_FOR_DELETE")
public class BookingQuestionDeleteRequestModel {
    private Integer studentId;
    private List<Integer> questionIds;
}
