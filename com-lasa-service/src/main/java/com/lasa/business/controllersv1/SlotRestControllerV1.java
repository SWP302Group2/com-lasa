/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllersv1;

import com.lasa.business.controllers.SlotOperations;
import com.lasa.business.services.SlotService;
import com.lasa.data.entity.Slot;
import com.lasa.data.entity.utils.criteria.SlotSearchCriteria;
import com.lasa.data.entity.utils.page.SlotPage;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api/v1/slots")
@Api(value = "slots", description = "About slots", tags = { "Slots Controller" })
public class SlotRestControllerV1 implements SlotOperations {

    private final SlotService service;

    @Autowired
    public SlotRestControllerV1(@Qualifier("SlotServiceImplV1") SlotService service) {
        this.service = service;
    }


    @Override
    public ResponseEntity<?> findAll(SlotSearchCriteria searchCriteria, SlotPage slotPage) {
        if(slotPage.isPaging())
            return ResponseEntity.ok(service.findAll(searchCriteria, slotPage));
        else
            return ResponseEntity.ok(service.findAll(searchCriteria));
    }

    @Override
    public Slot findById(Integer id) {
        return service.findById(id);
    }

    @Override
    public List<Slot> createSlots(List<Slot> slots) {
        return service.createSlots(slots);
    }

    @Override
    public List<Slot> updateSlots(List<Slot> slots) {
        return service.updateSlots(slots);
    }

    @Override
    public void deleteSlots(List<Integer> ids) {
        service.deleteSlots(ids);
    }

}
