package com.lasa.data.model.view;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.lasa.data.model.entity.SlotTopicDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id", scope = SlotTopicDetailViewModel.class)
public class SlotTopicDetailViewModel implements Serializable {
    private SlotViewModel slot;
    private TopicViewModel topic;
    private Integer slotId;
    private Integer topicId;

    public SlotTopicDetailViewModel(SlotTopicDetail slotTopicDetail) {
        this.slot = new SlotViewModel(slotTopicDetail.getSlot());
        this.topic = new TopicViewModel(slotTopicDetail.getTopic());
    }

    public SlotTopicDetailViewModel(Integer slotId, Integer topicId) {
        this.slotId = slotId;
        this.topicId = topicId;
    }
}
