package com.lasa.data.dto;

import com.lasa.data.entity.Slot;
import com.lasa.data.entity.SlotTopicDetail;
import com.lasa.data.entity.Topic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SlotTopicDetailDTO {
    private Slot slot;
    private Topic topic;

    public SlotTopicDetailDTO(SlotTopicDetail slotTopicDetail) {
        this.slot = slotTopicDetail.getSlot();
        this.topic = slotTopicDetail.getTopic();
    }
}
