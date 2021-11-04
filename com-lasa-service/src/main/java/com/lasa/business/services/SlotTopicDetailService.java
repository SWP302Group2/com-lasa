/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.model.entity.key.SlotTopicDetailKey;
import com.lasa.data.model.request.SlotTopicDetailRequestModel;
import com.lasa.data.model.utils.criteria.SlotTopicDetailSearchCriteria;
import com.lasa.data.model.utils.page.SlotTopicDetailPage;
import com.lasa.data.model.view.SlotTopicDetailViewModel;
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
    
    SlotTopicDetailViewModel findById(SlotTopicDetailKey id);
    
    List<SlotTopicDetailViewModel> createSlotTopicDetails(List<SlotTopicDetailRequestModel> details);
    
    List<SlotTopicDetailViewModel> updateSlotTopicDetails(List<SlotTopicDetailRequestModel> details);
    
    void deleteSlotTopicDetails(List<SlotTopicDetailKey> ids);
    
}
