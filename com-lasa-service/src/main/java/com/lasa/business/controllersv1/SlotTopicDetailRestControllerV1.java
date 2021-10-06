/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllersv1;

import com.lasa.business.controllers.SlotTopicDetailOperations;
import com.lasa.data.entity.SlotTopicDetail;
import com.lasa.data.entity.key.SlotTopicDetailKey;
import com.lasa.business.services.SlotTopicDetailService;
import java.util.List;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.*;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api/v1/slot-topic-details")
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
@Api(value = "slot-topic-details", description = "About topic details for slot", tags = { "Slot topic details" })
public class SlotTopicDetailRestControllerV1 implements SlotTopicDetailOperations {

    private final SlotTopicDetailService service;

    @Autowired
    public SlotTopicDetailRestControllerV1(@Qualifier("SlotTopicDetailServiceImplV1") SlotTopicDetailService service) {
        this.service = service;
    }

    @Override
    public List<SlotTopicDetail> findAll() {
        return service.findAll();
    }

    @Override
    public SlotTopicDetail findById(SlotTopicDetailKey id) {
        return service.findById(id);
    }

    @Override
    public List<SlotTopicDetail> createSlotTopicDetails(List<SlotTopicDetail> details) {
        return service.createSlotTopicDetails(details);
    }

    @Override
    public List<SlotTopicDetail> updateSlotTopicDetails(List<SlotTopicDetail> details) {
        return service.updateSlotTopicDetails(details);
    }

    @Override
    public void deleteSlotTopicDetails(List<SlotTopicDetailKey> ids) {
        service.deleteSlotTopicDetails(ids);
    }

}
