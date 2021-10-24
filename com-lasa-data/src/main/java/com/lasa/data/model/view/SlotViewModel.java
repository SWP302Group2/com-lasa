package com.lasa.data.model.view;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.lasa.data.model.entity.Slot;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id", scope = SlotViewModel.class)
public class SlotViewModel implements Serializable {
    private Integer id;
    private Integer lecturerId;
    private LocalDateTime timeStart;
    private Collection<TopicViewModel> topics = new ArrayList<>();
    private Collection<BookingRequestViewModel> bookingRequests = new ArrayList<>();
    private LecturerViewModel lecturer;
    private LocalDateTime timeEnd;

    public SlotViewModel(Slot slot) {
        this.id = slot.getId();
        this.lecturerId = slot.getLecturerId();
        this.timeStart = slot.getTimeStart();
        this.timeEnd = slot.getTimeEnd();
    }

    @Builder
    public SlotViewModel(Integer id, Integer lecturerId, LocalDateTime timeStart, Collection<TopicViewModel> topics, LocalDateTime timeEnd) {
        this.id = id;
        this.lecturerId = lecturerId;
        this.timeStart = timeStart;
        this.topics = topics;
        this.timeEnd = timeEnd;
    }

    public SlotViewModel SlotDTOWithTopics(Slot slot) {
        return SlotViewModel.builder()
                .id(slot.getId())
                .lecturerId(slot.getLecturerId())
                .timeStart(slot.getTimeStart())
                .timeEnd(slot.getTimeEnd())
                .topics(slot.getTopics()
                        .stream()
                        .map(t -> {
                                return new TopicViewModel(t.getTopic());
                            })
                        .collect(Collectors.toList()))
                .build();
    }

    public void addTopic(TopicViewModel dto) {
        topics.add(dto);
    }

    public void addBookingRequest(BookingRequestViewModel dto) {
        bookingRequests.add(dto);
    }
}
