package com.lasa.data.model.request;

import com.lasa.data.model.entity.Slot;
import com.lasa.data.model.entity.SlotTopicDetail;
import com.lasa.data.model.entity.Topic;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SlotTopicDetailRequestModel {
    private Integer slotId;
    private Integer topicId;

    @Builder
    public SlotTopicDetailRequestModel(Integer slotId, Integer topicId) {
        this.slotId = slotId;
        this.topicId = topicId;
    }

    public SlotTopicDetail toEntity() {
        return SlotTopicDetail.builder()
                .slot(Slot.builder()
                        .id(slotId)
                        .build())
                .topic(Topic.builder()
                        .id(topicId)
                        .build())
                .build();
    }

    public SlotTopicDetail toEntity(Integer slotId, Integer topicId) {
        this.slotId = slotId;
        this.topicId = topicId;
        return toEntity();
    }
}
