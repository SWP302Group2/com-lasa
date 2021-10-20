package com.lasa.data.dto;

import com.lasa.data.entity.BookingRequest;
import com.lasa.data.entity.Question;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
    private Integer id;
    private BookingRequest bookingRequest;
    private String content;

    public QuestionDTO(Question question) {
        this.id = question.getId();
        this.bookingRequest = question.getBookingRequest();
        this.content = question.getContent();
    }
}
