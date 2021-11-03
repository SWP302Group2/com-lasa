/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.model.request.SlotBookingRequestModel;
import com.lasa.data.model.request.SlotRequestModel;
import com.lasa.data.model.view.SlotViewModel;
import com.lasa.data.model.utils.criteria.SlotSearchCriteria;
import com.lasa.data.model.utils.page.SlotPage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

/**
 *
 * @author hai
 */
@Service
public interface SlotService {
    
    Page<SlotViewModel> findWithArguments(SlotSearchCriteria searchCriteria, SlotPage slotPage);

    List<SlotViewModel> findWithArguments(SlotSearchCriteria searchCriteria);

    SlotViewModel findById(Integer id);

    Boolean verifySlot(SlotRequestModel slot);

    SlotViewModel createSlot(SlotRequestModel slot);

    SlotViewModel updateSlots(SlotRequestModel slots);

    SlotViewModel acceptDenyBooking(SlotBookingRequestModel model);
    
    void deleteSlots(List<Integer> ids);
}
