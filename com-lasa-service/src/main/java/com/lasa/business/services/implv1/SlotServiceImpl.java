/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services.implv1;

import com.lasa.business.services.LecturerService;
import com.lasa.business.services.SlotService;
import com.lasa.business.services.SlotTopicDetailService;
import com.lasa.data.dto.LecturerDTO;
import com.lasa.data.dto.SlotDTO;
import com.lasa.data.dto.SlotTopicDetailDTO;
import com.lasa.data.entity.Slot;
import com.lasa.data.entity.utils.criteria.LecturerSearchCriteria;
import com.lasa.data.entity.utils.criteria.SlotSearchCriteria;
import com.lasa.data.entity.utils.criteria.SlotTopicDetailSearchCriteria;
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
    @Autowired
    public SlotServiceImpl(
            SlotRepository slotRepository,
            @Qualifier("LecturerServiceImplV1") LecturerService lecturerService,
            @Qualifier("SlotTopicDetailServiceImplV1") SlotTopicDetailService slotTopicDetailService
                           ) {
        this.slotRepository = slotRepository;
        this.lecturerService = lecturerService;
        this.slotTopicDetailService = slotTopicDetailService;
    }


    @Override
    public Page<SlotDTO> findWithArguments(SlotSearchCriteria searchCriteria, SlotPage slotPage) {

        Pageable pageable = PageRequest.of(slotPage.getPage(), slotPage.getSize(), Sort.by(slotPage.getOrderBy(), slotPage.getSortBy()));

        Page<SlotDTO> page = slotRepository
                .findAll(SlotSpecification.searchSpecification(searchCriteria), pageable)
                .map(t -> new SlotDTO(t));

        if(searchCriteria.getGetLecturer().equals(true))
            mapLecturer(page);

        if(searchCriteria.getGetTopic().equals(true))
            mapTopic(page);

        return page;
    }

    @Override
    public List<SlotDTO> findWithArguments(SlotSearchCriteria searchCriteria) {
        List<SlotDTO> list = slotRepository
                .findAll(SlotSpecification.searchSpecification(searchCriteria))
                .stream()
                .map(t -> new SlotDTO(t))
                .collect(Collectors.toList());

        if(searchCriteria.getGetLecturer().equals(true))
            mapLecturer(list);

        if(searchCriteria.getGetTopic().equals(true))
            mapTopic(list);

        return list;
    }

    private void mapLecturer(Page<SlotDTO> page) {
        List<Integer> lecIds = page.stream()
                .map(t -> t.getLecturerId())
                .collect(Collectors.toList());

        List<LecturerDTO> lecturers = getLecturers(lecIds);

        page.stream()
                .forEach(t -> {
                    t.setLecturer(lecturers.stream()
                            .filter(x -> x.getId().equals(t.getLecturerId()))
                            .findAny()
                            .get());
                });
    }

    private void mapTopic(Page<SlotDTO> origin) {
        List<Integer> slotIds = origin.stream()
                .map(t -> t.getId())
                .collect(Collectors.toList());

        List<SlotTopicDetailDTO> slotTopicDetails = getTopics(slotIds);

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

    private void mapTopic(List<SlotDTO> origin) {
        List<Integer> slotIds = origin.stream()
                .map(t -> t.getId())
                .collect(Collectors.toList());

        SlotTopicDetailSearchCriteria searchCriteria = new SlotTopicDetailSearchCriteria();
        searchCriteria.setSId(slotIds);

        List<SlotTopicDetailDTO> slotTopicDetails = getTopics(slotIds);

        origin.stream()
                .forEach(t -> {
                    slotTopicDetails.stream()
                            .forEach(x -> {
                                if(x.getSlot().getId().equals(t.getId()))
                                    t.addTopic(x.getTopic());
                            });
                });
    }

    private void mapLecturer(List<SlotDTO> origin) {
        List<Integer> lecIds = origin.stream()
                .map(t -> t.getLecturerId())
                .collect(Collectors.toList());

        List<LecturerDTO> lecturers = getLecturers(lecIds);

        origin.stream()
                .forEach(t -> {
                    t.setLecturer(lecturers.stream()
                            .filter(x -> x.getId().equals(t.getLecturerId()))
                            .findAny()
                            .get());
                });
    }

    private List<LecturerDTO> getLecturers(List<Integer> ids) {
        LecturerSearchCriteria searchCriteria = LecturerSearchCriteria.builder()
                .lecId(ids)
                .build();
        return lecturerService.findAll(searchCriteria);
    }

    private List<SlotTopicDetailDTO> getTopics(List<Integer> ids) {
        SlotTopicDetailSearchCriteria searchCriteria = SlotTopicDetailSearchCriteria.builder()
                .sId(ids)
                .getTopicAndSlot(true)
                .build();
        return (List<SlotTopicDetailDTO>) slotTopicDetailService.findAllWithArgument(searchCriteria);
    }

    @Override
    public SlotDTO findById(Integer id) {
        Optional<Slot> slot = slotRepository.findById(id);
        if(slot.isPresent())
            return new SlotDTO(slot.get());
        else
            return null;
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
