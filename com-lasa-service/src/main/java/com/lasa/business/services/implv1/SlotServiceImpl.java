/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services.implv1;

import com.lasa.business.services.SlotService;
import com.lasa.data.entity.Slot;
import com.lasa.data.entity.utils.criteria.SlotSearchCriteria;
import com.lasa.data.entity.utils.page.SlotPage;
import com.lasa.data.entity.utils.specification.SlotSpecification;
import com.lasa.data.repo.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 *
 * @author hai
 */
@Component
@Qualifier("SlotServiceImplV1")
public class SlotServiceImpl implements SlotService {

    private final SlotRepository slotRepository ;

    @Autowired
    public SlotServiceImpl(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }


    @Override
    public Page<Slot> findAll(SlotSearchCriteria searchCriteria, SlotPage slotPage) {
        Pageable pageable = PageRequest.of(slotPage.getPage(), slotPage.getSize(), Sort.by(slotPage.getOrderBy(), slotPage.getSortBy()));
        return slotRepository.findAll(SlotSpecification.searchSpecification(searchCriteria), pageable);
    }

    @Override
    public List<Slot> findAll(SlotSearchCriteria searchCriteria) {
        return slotRepository.findAll(SlotSpecification.searchSpecification(searchCriteria));
    }

    @Override
    public Slot findById(Integer id) {
        return slotRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean verifySlot(Slot slot) {
        if(slotRepository.countSlotByTimeStartAndTimeEndAndLecturerIdIn(slot.getTimeStart(), slot.getTimeEnd(), slot.getLecturerId()) > 0)
            return false;
        return true;
    }

    @Override
    public Slot createSlot(Slot slot) {
        return slotRepository.save(slot);
    }

    @Override
    public List<Slot> createSlots(List<Slot> slots) {
        return slotRepository.saveAll(slots);
    }

    @Override
    public List<Slot> updateSlots(List<Slot> slots) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteSlots(List<Integer> ids) {
        slotRepository.deleteAllById(ids);
    }
    
}
