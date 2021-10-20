package com.lasa.data.dto;

import com.lasa.data.entity.BookingRequest;
import com.lasa.data.entity.LecturerTopicDetail;
import com.lasa.data.entity.SlotTopicDetail;
import com.lasa.data.entity.Topic;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicDTO {
    private Integer id;
    private String name;
    private Collection<LecturerTopicDetail> lecturers;
    private String majorId;
    private Collection<BookingRequest> bookingRequests;
    private Collection<SlotTopicDetail> slots;
    private String courseId;
    private Integer status;
    private String description;

    public TopicDTO(Topic topic) {
        this.id = topic.getId();
        this.name = topic.getName();
        this.lecturers = topic.getLecturers();
        this.majorId = topic.getMajorId();
        this.bookingRequests = topic.getBookingRequests();
        this.slots = topic.getSlots();
        this.courseId = topic.getCourseId();
        this.status = topic.getStatus();
        this.description = topic.getDescription();
    }
}
