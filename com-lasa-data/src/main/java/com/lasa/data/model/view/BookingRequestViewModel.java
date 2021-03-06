package com.lasa.data.model.view;

import com.lasa.data.model.entity.BookingRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestViewModel implements Serializable {
    private Integer id;
    private Integer studentId;
    private Integer status;
    private StudentViewModel student;
    private List<QuestionViewModel> questions = new ArrayList<>();
    private Integer topicId;
    private Integer slotId;
    private String title;
    private Integer rating;
    private LocalDateTime createTime;

    public BookingRequestViewModel(BookingRequest bookingRequest) {
        this.id = bookingRequest.getId();
        this.studentId = bookingRequest.getStudentId();
        this.status = bookingRequest.getStatus();
        this.topicId = bookingRequest.getTopicId();
        this.slotId = bookingRequest.getSlotId();
        this.title = bookingRequest.getTitle();
        this.rating = bookingRequest.getRating();
        this.createTime = bookingRequest.getCreateTime();
    }

    public void addQuestion(QuestionViewModel dto) {
        questions.add(dto);
    }
}
