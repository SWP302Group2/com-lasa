/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services.implv1;

import com.lasa.business.services.LecturerService;
import com.lasa.business.services.LecturerTopicDetailService;
import com.lasa.business.services.SlotService;
import com.lasa.business.services.SlotTopicDetailService;
import com.lasa.data.model.entity.*;
import com.lasa.data.model.request.SlotRequestModel;
import com.lasa.data.model.view.LecturerViewModel;
import com.lasa.data.model.view.SlotViewModel;
import com.lasa.data.model.view.SlotTopicDetailViewModel;
import com.lasa.data.model.utils.criteria.LecturerSearchCriteria;
import com.lasa.data.model.utils.criteria.SlotSearchCriteria;
import com.lasa.data.model.utils.criteria.SlotTopicDetailSearchCriteria;
import com.lasa.data.model.utils.page.SlotPage;
import com.lasa.data.model.utils.specification.SlotSpecification;
import com.lasa.data.repo.repository.SlotRepository;
import com.lasa.data.repo.repository.SlotTopicDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 *
 * @author hai
 */
@Component
@Qualifier("SlotServiceImplV1")
public class SlotServiceImpl implements SlotService {

    private final SlotRepository slotRepository ;
    private final SlotTopicDetailService slotTopicDetailService;
    private final LecturerService lecturerService;
    private final LecturerTopicDetailService lecturerTopicDetailService;
    private final SlotTopicDetailRepository slotTopicDetailRepository;

    @Autowired
    public SlotServiceImpl(
            SlotRepository slotRepository,
            @Qualifier("LecturerServiceImplV1") LecturerService lecturerService,
            @Qualifier("SlotTopicDetailServiceImplV1") SlotTopicDetailService slotTopicDetailService,
            @Qualifier("LecturerTopicDetailServiceImplV1") LecturerTopicDetailService lecturerTopicDetailService,
            SlotTopicDetailRepository slotTopicDetailRepository
                           ) {
        this.slotRepository = slotRepository;
        this.lecturerService = lecturerService;
        this.slotTopicDetailService = slotTopicDetailService;
        this.lecturerTopicDetailService = lecturerTopicDetailService;
        this.slotTopicDetailRepository = slotTopicDetailRepository;
    }


    @Override
    public Page<SlotViewModel> findWithArguments(SlotSearchCriteria searchCriteria, SlotPage slotPage) {

        Pageable pageable = PageRequest.of(slotPage.getPage(), slotPage.getSize(), Sort.by(slotPage.getOrderBy(), slotPage.getSortBy()));

        Page<SlotViewModel> page = slotRepository
                .findAll(SlotSpecification.searchSpecification(searchCriteria), pageable)
                .map(t -> new SlotViewModel(t));

        if(searchCriteria.getGetLecturer().equals(true))
            mapLecturer(page);

        if(searchCriteria.getGetTopic().equals(true))
            mapTopic(page);

        return page;
    }

    @Override
    public List<SlotViewModel> findWithArguments(SlotSearchCriteria searchCriteria) {
        List<SlotViewModel> list = slotRepository
                .findAll(SlotSpecification.searchSpecification(searchCriteria))
                .stream()
                .map(t -> new SlotViewModel(t))
                .collect(Collectors.toList());

        if(searchCriteria.getGetLecturer().equals(true))
            mapLecturer(list);

        if(searchCriteria.getGetTopic().equals(true))
            mapTopic(list);

        return list;
    }

    private void mapLecturer(Page<SlotViewModel> page) {
        List<Integer> lecIds = page.stream()
                .map(t -> t.getLecturerId())
                .collect(Collectors.toList());

        List<LecturerViewModel> lecturers = getLecturers(lecIds);

        page.stream()
                .forEach(t -> {
                    t.setLecturer(lecturers.stream()
                            .filter(x -> x.getId().equals(t.getLecturerId()))
                            .findAny()
                            .get());
                });
    }

    private void mapTopic(Page<SlotViewModel> origin) {
        List<Integer> slotIds = origin.stream()
                .map(t -> t.getId())
                .collect(Collectors.toList());

        List<SlotTopicDetailViewModel> slotTopicDetails = getTopics(slotIds);

        origin.stream()
                .forEach(t -> {
                    slotTopicDetails.stream()
                            .forEach(x -> {
                                if(x.getSlot().getId().equals(t.getId())) {
                                    t.addTopic(x.getTopic());
                                }
                            });
                });
    }

    private void mapTopic(List<SlotViewModel> origin) {
        List<Integer> slotIds = origin.stream()
                .map(t -> t.getId())
                .collect(Collectors.toList());

        SlotTopicDetailSearchCriteria searchCriteria = new SlotTopicDetailSearchCriteria();
        searchCriteria.setSId(slotIds);

        List<SlotTopicDetailViewModel> slotTopicDetails = getTopics(slotIds);

        origin.stream()
                .forEach(t -> {
                    slotTopicDetails.stream()
                            .forEach(x -> {
                                if(x.getSlot().getId().equals(t.getId()))
                                    t.addTopic(x.getTopic());
                            });
                });
    }

    private void mapLecturer(List<SlotViewModel> origin) {
        List<Integer> lecIds = origin.stream()
                .map(t -> t.getLecturerId())
                .collect(Collectors.toList());

        List<LecturerViewModel> lecturers = getLecturers(lecIds);

        origin.stream()
                .forEach(t -> {
                    t.setLecturer(lecturers.stream()
                            .filter(x -> x.getId().equals(t.getLecturerId()))
                            .findAny()
                            .get());
                });
    }

    private List<LecturerViewModel> getLecturers(List<Integer> ids) {
        LecturerSearchCriteria searchCriteria = LecturerSearchCriteria.builder()
                .lecId(ids)
                .build();
        return lecturerService.findAll(searchCriteria);
    }

    private List<SlotTopicDetailViewModel> getTopics(List<Integer> ids) {
        SlotTopicDetailSearchCriteria searchCriteria = SlotTopicDetailSearchCriteria.builder()
                .sId(ids)
                .getTopicAndSlot(true)
                .build();
        return (List<SlotTopicDetailViewModel>) slotTopicDetailService.findAllWithArgument(searchCriteria);
    }

    @Override
    public SlotViewModel findById(Integer id) {
        Optional<Slot> slot = slotRepository.findById(id);
        if(slot.isPresent())
            return new SlotViewModel(slot.get());
        else
            return null;
    }

    @Override
    public Boolean verifySlot(SlotRequestModel slot) {
        if(slotRepository.countActiveSlotByTimeStartAndTimeEndAndLecturerId(slot.getTimeStart(), slot.getTimeEnd(), slot.getLecturerId()) > 0)
            return false;
        return true;
    }

    @Override
    public SlotViewModel createSlot(SlotRequestModel model) {
        return new SlotViewModel(slotRepository.save(model.toEntity()));
    }

    @Override
    @Transactional
    public SlotViewModel updateSlots(SlotRequestModel slotRequestModel) {
        Slot slot = slotRepository.findById(slotRequestModel.getId()).get();

        if(Objects.nonNull(slotRequestModel.getStatus())) {
            slot.setStatus(slotRequestModel.getStatus());
            if(slot.getStatus() == 0)
                //if slot status = 0 => denied all booking
                slot.getBookingRequests().forEach(t -> t.setStatus(-1));
        }

        return new SlotViewModel(slotRepository.save(slot));

    }

    @Override
    public void deleteSlots(List<Integer> ids) {
        slotRepository.deleteAllById(ids);
    }
    
}
