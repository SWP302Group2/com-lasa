/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.entity.SlotTopicDetail;
import com.lasa.data.entity.key.SlotTopicDetailKey;
import com.lasa.data.entity.utils.criteria.SlotTopicDetailSearchCriteria;
import com.lasa.data.entity.utils.page.SlotTopicDetailPage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author hai
 */
@Service
public interface SlotTopicDetailService {

    Page<?> findAllWithArgument(SlotTopicDetailPage slotTopicDetailPage, SlotTopicDetailSearchCriteria searchCriteria);

    List<?> findAllWithArgument(SlotTopicDetailSearchCriteria searchCriteria);
    
    SlotTopicDetail findById(SlotTopicDetailKey id);
    
    List<SlotTopicDetail> createSlotTopicDetails(List<SlotTopicDetail> details);
    
    List<SlotTopicDetail> updateSlotTopicDetails(List<SlotTopicDetail> details);
    
    void deleteSlotTopicDetails(List<SlotTopicDetailKey> ids);
    
}
