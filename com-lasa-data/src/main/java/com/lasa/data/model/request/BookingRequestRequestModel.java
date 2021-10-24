package com.lasa.data.model.request;

import com.lasa.data.model.entity.BookingRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestRequestModel {
    private Integer id;
    private Integer studentId;
    private Integer status;
    private List<QuestionRequestModel> questions;
    private Integer topicId;
    private Integer slotId;
    private String title;

    public BookingRequest toEntity() {
        return BookingRequest.builder()
                .id(id)
                .studentId(id)
                .status(status)
                .topicId(topicId)
                .slotId(slotId)
                .title(title)
                .build();
    }
}
