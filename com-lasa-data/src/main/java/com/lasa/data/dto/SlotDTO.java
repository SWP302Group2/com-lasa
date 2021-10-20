package com.lasa.data.dto;

import com.lasa.data.entity.BookingRequest;
import com.lasa.data.entity.Slot;
import com.lasa.data.entity.SlotTopicDetail;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SlotDTO {
    private Integer id;
    private Integer lecturerId;
    private Collection<BookingRequest> bookingRequests;
    private Collection<SlotTopicDetail> topics;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;

    public SlotDTO(Slot slot) {
        this.id = slot.getId();
        this.lecturerId = slot.getLecturerId();
        this.bookingRequests = slot.getBookingRequests();
        this.topics = slot.getTopics();
        this.timeStart = slot.getTimeStart();
        this.timeEnd = slot.getTimeEnd();
    }
}
