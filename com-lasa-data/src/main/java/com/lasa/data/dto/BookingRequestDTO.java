package com.lasa.data.dto;

import com.lasa.data.entity.BookingRequest;
import com.lasa.data.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDTO implements Serializable {
    private Integer id;
    private Integer studentId;
    private Integer status;
    private List<QuestionDTO> questions;
    private Integer topicId;
    private Integer slotId;
    private String title;

    public BookingRequestDTO(BookingRequest bookingRequest) {
        this.id = bookingRequest.getId();
        this.studentId = bookingRequest.getStudentId();
        this.status = bookingRequest.getStatus();
        this.questions =  bookingRequest
                .getQuestions()
                .stream()
                .map(t -> new QuestionDTO(t))
                .collect(Collectors.toList());
        this.topicId = bookingRequest.getTopicId();
        this.slotId = bookingRequest.getSlotId();
        this.title = bookingRequest.getTitle();
    }
}
