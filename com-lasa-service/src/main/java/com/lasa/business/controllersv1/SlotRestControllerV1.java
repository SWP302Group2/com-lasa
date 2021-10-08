/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllersv1;

import com.lasa.business.controllers.SlotOperations;
import com.lasa.data.entity.Slot;
import com.lasa.business.services.SlotService;
import java.util.List;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.*;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api/v1/slots")
@CrossOrigin(
        allowCredentials = "true",
        origins = {"http://localhost:3000", "http://localhost:5500", "https://lasa-fpt.web.app"},
        allowedHeaders = {
            CONTENT_TYPE,
            CONTENT_LENGTH,
            HOST,
            USER_AGENT,
            ACCEPT,
            ACCEPT_ENCODING,
            CONNECTION,
            AUTHORIZATION
        },
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS}
)
@Api(value = "slots", description = "About slots", tags = {"Slots Controller"})
public class SlotRestControllerV1 implements SlotOperations {

    private final SlotService service;

    @Autowired
    public SlotRestControllerV1(@Qualifier("SlotServiceImplV1") SlotService service) {
        this.service = service;
    }

    @Override
    public List<Slot> findAll() {
        return service.findAll();
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
//
//    @Override
//    public ResponseEntity<?> getSlotsByStatus(Integer pageNumber, Integer pageSize, Integer status) {
//        return ResponseEntity.ok(service.findSlotsByStatus(pageSize, pageSize, status));
//    }



}
