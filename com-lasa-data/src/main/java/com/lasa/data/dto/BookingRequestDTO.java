package com.lasa.data.dto;

import com.lasa.data.entity.BookingRequest;
import com.lasa.data.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDTO {
    private Integer id;
    private Integer studentId;
    private Integer status;
    private List<Question> questions;
    private Integer topicId;
    private Integer slotId;
    private String title;

    public BookingRequestDTO(BookingRequest bookingRequest) {
        this.id = bookingRequest.getId();
        this.studentId = bookingRequest.getStudentId();
        this.status = bookingRequest.getStatus();
        this.questions = bookingRequest.getQuestions();
        this.topicId = bookingRequest.getTopicId();
        this.slotId = bookingRequest.getSlotId();
        this.title = bookingRequest.getTitle();
    }
}
