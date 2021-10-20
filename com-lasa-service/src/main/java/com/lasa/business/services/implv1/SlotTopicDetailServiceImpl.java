/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services.implv1;

import com.lasa.business.services.SlotTopicDetailService;
import com.lasa.data.dto.SlotTopicDetailDTO;
import com.lasa.data.entity.SlotTopicDetail;
import com.lasa.data.entity.key.SlotTopicDetailKey;
import com.lasa.data.entity.utils.criteria.SlotTopicDetailSearchCriteria;
import com.lasa.data.entity.utils.dto.SlotTopicDetailSimple;
import com.lasa.data.entity.utils.page.SlotTopicDetailPage;
import com.lasa.data.entity.utils.specification.SlotTopicDetailSpecification;
import com.lasa.data.repo.repository.SlotTopicDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author hai
 */
@Component
@Qualifier("SlotTopicDetailServiceImplV1")
public class SlotTopicDetailServiceImpl implements SlotTopicDetailService {

    private final SlotTopicDetailRepository detailRepository;

    @Autowired
    public SlotTopicDetailServiceImpl(SlotTopicDetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    @Override
    public Page<?> findAllWithArgument(SlotTopicDetailPage slotTopicDetailPage, SlotTopicDetailSearchCriteria searchCriteria) {
        Pageable pageable = PageRequest.of(slotTopicDetailPage.getPage(), slotTopicDetailPage.getSize());
        Page<SlotTopicDetail> page = detailRepository.findAll(SlotTopicDetailSpecification.searchSpecification(searchCriteria), pageable);

        if(searchCriteria.getGetTopicAndSlot().equals(true))
            return page.map(t -> new SlotTopicDetailDTO(t));

        return page.map(
                slotTopicDetail -> {
                    SlotTopicDetailSimple dto = SlotTopicDetailSimple.builder()
                            .slotId(slotTopicDetail.getSlot().getId())
                            .topicId(slotTopicDetail.getTopic().getId())
                            .build();
                    return dto;
                }
        );
    }

    @Override
    public List<?> findAllWithArgument(SlotTopicDetailSearchCriteria searchCriteria) {
        List<SlotTopicDetail> list = detailRepository.findAll(SlotTopicDetailSpecification.searchSpecification(searchCriteria));

        if(searchCriteria.getGetTopicAndSlot().equals(true))
            return list.stream()
                    .map(t -> new SlotTopicDetailDTO(t))
                    .collect(Collectors.toList());

        return list.stream()
                .map( slotTopicDetail -> {
                    SlotTopicDetailSimple dto = SlotTopicDetailSimple.builder()
                            .slotId(slotTopicDetail.getSlot().getId())
                            .topicId(slotTopicDetail.getTopic().getId())
                            .build();
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public SlotTopicDetail findById(SlotTopicDetailKey id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public List<SlotTopicDetail> createSlotTopicDetails(List<SlotTopicDetail> details) {
        return detailRepository.saveAll(details);
    }

    @Override
    public List<SlotTopicDetail> updateSlotTopicDetails(List<SlotTopicDetail> details) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteSlotTopicDetails(List<SlotTopicDetailKey> ids) {
        detailRepository.deleteAllById(ids);
    }
    
}
