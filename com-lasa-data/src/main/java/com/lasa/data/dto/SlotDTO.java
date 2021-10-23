package com.lasa.data.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.lasa.data.entity.BookingRequest;
import com.lasa.data.entity.Slot;
import com.lasa.data.entity.SlotTopicDetail;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id", scope = SlotDTO.class)
public class SlotDTO implements Serializable {
    private Integer id;
    private Integer lecturerId;
    private LocalDateTime timeStart;
    private Collection<TopicDTO> topics = new ArrayList<>();
    private Collection<BookingRequestDTO> bookingRequests = new ArrayList<>();
    private LecturerDTO lecturer;
    private LocalDateTime timeEnd;

    public SlotDTO(Slot slot) {
        this.id = slot.getId();
        this.lecturerId = slot.getLecturerId();
        this.timeStart = slot.getTimeStart();
        this.timeEnd = slot.getTimeEnd();
    }

    @Builder
    public SlotDTO(Integer id, Integer lecturerId, LocalDateTime timeStart, Collection<TopicDTO> topics, LocalDateTime timeEnd) {
        this.id = id;
        this.lecturerId = lecturerId;
        this.timeStart = timeStart;
        this.topics = topics;
        this.timeEnd = timeEnd;
    }

    public SlotDTO SlotDTOWithTopics(Slot slot) {
        return SlotDTO.builder()
                .id(slot.getId())
                .lecturerId(slot.getLecturerId())
                .timeStart(slot.getTimeStart())
                .timeEnd(slot.getTimeEnd())
                .topics(slot.getTopics()
                        .stream()
                        .map(t -> {
                                return new TopicDTO(t.getTopic());
                            })
                        .collect(Collectors.toList()))
                .build();
    }

    public void addTopic(TopicDTO dto) {
        topics.add(dto);
    }

    public void addBookingRequest(BookingRequestDTO dto) {
        bookingRequests.add(dto);
    }
}
