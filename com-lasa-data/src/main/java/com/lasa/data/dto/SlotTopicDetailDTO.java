package com.lasa.data.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.lasa.data.entity.Slot;
import com.lasa.data.entity.SlotTopicDetail;
import com.lasa.data.entity.Topic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id", scope = SlotTopicDetailDTO.class)
public class SlotTopicDetailDTO implements Serializable {
    private SlotDTO slot;
    private TopicDTO topic;

    public SlotTopicDetailDTO(SlotTopicDetail slotTopicDetail) {
        this.slot = new SlotDTO(slotTopicDetail.getSlot());
        this.topic = new TopicDTO(slotTopicDetail.getTopic());
    }
}
