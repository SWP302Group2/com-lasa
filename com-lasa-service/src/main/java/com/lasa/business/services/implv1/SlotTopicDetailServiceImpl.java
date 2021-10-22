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
import java.util.Objects;
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


        if (searchCriteria.getGetTopicAndSlot().equals(true)) {
            if (Objects.nonNull(searchCriteria.getTopicId()) && Objects.nonNull(searchCriteria.getTopicId()))
                return convertToDTO(detailRepository.findAllSlotAndTopicByTopicIdAndSlotId(searchCriteria.getSId(), searchCriteria.getTopicId(), pageable));

            else if (Objects.nonNull(searchCriteria.getTopicId()))
                return convertToDTO(detailRepository.findAllSlotAndTopicByTopicId(searchCriteria.getTopicId(), pageable));

            else if (Objects.nonNull(searchCriteria.getSId()))
                return convertToDTO(detailRepository.findAllSlotAndTopicBySlotId(searchCriteria.getSId(), pageable));

            else
                return convertToDTO(detailRepository.findAllSlotAndTopic(pageable));

        }

        Page<SlotTopicDetail> page = detailRepository.findAll(SlotTopicDetailSpecification.searchSpecification(searchCriteria), pageable);
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


        if (searchCriteria.getGetTopicAndSlot().equals(true)) {
            if (Objects.nonNull(searchCriteria.getTopicId()) && Objects.nonNull(searchCriteria.getTopicId()))
                return convertToDTO(detailRepository.findAllSlotAndTopicByTopicIdAndSlotId(searchCriteria.getTopicId(), searchCriteria.getSId()));

            else if (Objects.nonNull(searchCriteria.getTopicId()))
                return convertToDTO(detailRepository.findAllSlotAndTopicByTopicId(searchCriteria.getTopicId()));

            else if (Objects.nonNull(searchCriteria.getSId()))
                return convertToDTO(detailRepository.findAllSlotAndTopicBySlotId(searchCriteria.getSId()));

            else
                return convertToDTO(detailRepository.findAllSlotAndTopic());
        }


        List<SlotTopicDetail> list = detailRepository.findAll(SlotTopicDetailSpecification.searchSpecification(searchCriteria));
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
    private List<SlotTopicDetailDTO> convertToDTO(List<SlotTopicDetail> origin) {
        return origin.stream().map(t -> new SlotTopicDetailDTO(t))
                    .collect(Collectors.toList());
    }

    private Page<SlotTopicDetailDTO> convertToDTO(Page<SlotTopicDetail> origin) {
        return origin.map(t -> new SlotTopicDetailDTO(t));
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
