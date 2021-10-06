/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.entity.key;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author hai
 */
@Embeddable
@NoArgsConstructor
@Data
public class SlotTopicDetailKey implements Serializable {
    
    @Column(name = "slotid")
    private Integer slotId;
    
    @Column(name = "topicid")
    private Integer topicId;

    @Builder
    public SlotTopicDetailKey(Integer slotId, Integer topicId) {
        this.slotId = slotId;
        this.topicId = topicId;
    }
    
}
