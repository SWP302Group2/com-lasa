package com.lasa.data.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lasa.data.model.entity.BookingRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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
    private Integer rating;
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
                .rating(rating)
                .createTime(createTime)
                .build();
    }
}
