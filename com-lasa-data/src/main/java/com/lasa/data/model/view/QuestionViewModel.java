package com.lasa.data.model.view;

import com.lasa.data.model.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionViewModel implements Serializable {
    private Integer bookingId;
    private Integer id;
    private String content;

    public QuestionViewModel(Question question) {
        this.bookingId = question.getBookingRequest().getId();
        this.id = question.getId();
        this.content = question.getContent();
    }
}
