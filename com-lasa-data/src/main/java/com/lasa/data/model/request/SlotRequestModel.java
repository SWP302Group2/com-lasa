package com.lasa.data.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lasa.data.model.entity.Slot;
import com.lasa.data.validator.*;
import com.lasa.data.validator.group.PostValidator;
import com.lasa.data.validator.group.PatchValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ValidTimeStartAndTimeEnd(groups = PostValidator.class, message = "TIME_START_AND_TIME_END_NOT_VALID")
@ValidSlotCreate(groups = PostValidator.class, message = "SLOT_DUPLICATE_OR_NOT_VALID")
@ValidSlotTopicForCreateSlot(groups = PostValidator.class, message = "TOPICS_EMPTY_OR_NOT_VALID")
@ValidSlotUpdate(groups = PatchValidator.class, message = "SLOT_NOT_AVAILABLE_FOR_UPDATE")
@ValidTopicUpdate(groups = PatchValidator.class, message = "TOPIC_DUPLICATE_OR_NOT_VALID")
public class SlotRequestModel {
    private Integer id;
    private Integer lecturerId;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime timeStart;
    private List<Integer> topics;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime timeEnd;

    @ValidOneOf(value = 0, message = "STATUS_NOT_VALID", groups = PatchValidator.class)
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
