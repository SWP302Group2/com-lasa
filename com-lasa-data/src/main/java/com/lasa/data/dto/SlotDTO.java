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
import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id", scope = SlotDTO.class)
public class SlotDTO implements Serializable {
    private Integer id;
    private Integer lecturerId;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;

    public SlotDTO(Slot slot) {
        this.id = slot.getId();
        this.lecturerId = slot.getLecturerId();
        this.timeStart = slot.getTimeStart();
        this.timeEnd = slot.getTimeEnd();
    }
}
