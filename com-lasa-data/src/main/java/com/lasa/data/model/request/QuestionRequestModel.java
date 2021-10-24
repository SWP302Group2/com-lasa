package com.lasa.data.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lasa.data.model.entity.BookingRequest;
import com.lasa.data.model.entity.Question;
import com.lasa.data.model.view.BookingRequestViewModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequestModel {
    private Integer id;
    private Integer bookingId;
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
