package com.lasa.data.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.lasa.data.model.entity.Slot;
import com.lasa.data.validator.ValidSlotCreate;
import com.lasa.data.validator.ValidSlotTopicForCreateSlot;
import com.lasa.data.validator.ValidTimeStartAndTimeEnd;
import com.lasa.data.validator.group.PostValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ValidTimeStartAndTimeEnd(groups = PostValidator.class, message = "TIME_START_AND_TIME_END_NOT_VALID")
@ValidSlotCreate(groups = PostValidator.class, message = "SLOT_DUPLICATE_OR_NOT_VALID")
@ValidSlotTopicForCreateSlot(groups = PostValidator.class, message = "TOPICS_EMPTY_OR_NOT_VALID")
public class SlotRequestModel {
    private Integer id;
    private Integer lecturerId;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime timeStart;
    private List<Integer> topics;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime timeEnd;
    private Integer status;

    public Slot toEntity() {
        return Slot.builder()
                .id(id)
                .lecturerId(lecturerId)
                .timeStart(timeStart)
                .timeEnd(timeEnd)
                .status(status)
                .build();
    }
}
