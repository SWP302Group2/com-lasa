package com.lasa.data.model.request;

import com.lasa.data.model.entity.BookingRequest;
import com.lasa.data.model.entity.Question;
import com.lasa.data.validator.group.PostValidator;
import com.lasa.data.validator.group.PatchValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequestModel {

    private Integer id;

    private Integer bookingId;

    @NotEmpty(groups = PostValidator.class)
    private String content;

    public Question toEntity() {
        return Question.builder()
                .id(id)
                .bookingRequest(BookingRequest.builder()
                        .id(bookingId)
                        .build())
                .content(content)
                .build();
    }

    public Question toEntity(BookingRequest bookingRequest) {
        return Question
                .builder()
                .bookingRequest(bookingRequest)
                .content(content)
                .build();
    }

}
