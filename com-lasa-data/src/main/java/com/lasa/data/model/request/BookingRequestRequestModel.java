package com.lasa.data.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lasa.data.model.entity.BookingRequest;
import com.lasa.data.validator.*;
import com.lasa.data.validator.group.PostValidator;
import com.lasa.data.validator.group.PutValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ValidBookingTopicId(groups = {PostValidator.class, PutValidator.class}, message = "TOPIC_NOT_VALID")
@ValidBookingRequest(groups = PostValidator.class, message = "BOOKING_REQUEST_DUPLICATED")
@ValidQuestionUpdate(groups = PutValidator.class, message = "QUESTION_NOT_FOUND_OR_NOT_AVAILABLE")
@ValidBookingRequestRate(groups = PutValidator.class, message = "BOOKING_REQUEST_NOT_AVAILABLE_FOR_RATE")
public class BookingRequestRequestModel {

    @NotNull(groups = PutValidator.class, message = "ID_IS_NULL")
    @ValidBookingRequestUpdate(groups = PutValidator.class, message = "BOOKING_REQUEST_NOT_FOUND")
    private Integer id;

    @NotNull(groups = PostValidator.class, message = "STUDENT_ID_IS_NULL")
    private Integer studentId;

    @ValidOneOf(value = 0, groups = PutValidator.class)
    private Integer status;

    @NotEmpty(groups = PostValidator.class, message = "QUESTIONS_IS_EMPTY")
    @Size(max = 5, message = "QUESTIONS_IS_OVERFLOW")
    private List<@Valid QuestionRequestModel> questions;

    private Integer topicId;

    @ValidSlotId(groups = PostValidator.class, message = "SLOT_NOT_VALID_OR_NOT_AVAILABLE")
    private Integer slotId;

    @NotEmpty(groups = PostValidator.class, message = "TITLE_IS_EMPTY")
    private String title;

    private Integer rating ;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime createTime;

    public BookingRequest toEntity() {
        return BookingRequest.builder()
                .id(id)
                .studentId(studentId)
                .status(status)
                .topicId(topicId)
                .slotId(slotId)
                .title(title)
                .createTime(createTime)
                .build();
    }

}
