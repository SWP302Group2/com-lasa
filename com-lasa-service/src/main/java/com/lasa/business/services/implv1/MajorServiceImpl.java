/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services.implv1;

import com.lasa.business.services.MajorService;
import com.lasa.data.dto.MajorDTO;
import com.lasa.data.entity.Major;
import com.lasa.data.entity.utils.criteria.MajorSearchCriteria;
import com.lasa.data.entity.utils.page.MajorPage;
import com.lasa.data.entity.utils.projection.MajorWithSimpleTopic;
import com.lasa.data.entity.utils.specification.MajorSpecification;
import com.lasa.data.repo.repository.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author hai
 */
@Component
@Qualifier("MajorServiceImplV1")
public class MajorServiceImpl implements MajorService {

    private final MajorRepository majorRepository;

    @Autowired
    public MajorServiceImpl(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }


    @Override
    public Page<MajorDTO> findAll(MajorPage majorPage, MajorSearchCriteria searchCriteria) {
        Pageable pageable = PageRequest.of(majorPage.getPage(), majorPage.getSize(), Sort.by(majorPage.getOrderBy(), majorPage.getSortBy()));
        return majorRepository
                .findAll(MajorSpecification.searchSpecification(searchCriteria), pageable)
                .map(t -> new MajorDTO(t));
    }

    @Override
    public List<MajorDTO> findAll(MajorSearchCriteria searchCriteria) {
        return majorRepository
                .findAll(MajorSpecification.searchSpecification(searchCriteria))
                .stream()
                .map(t -> new MajorDTO(t))
                .collect(Collectors.toList());
    }

    @Override
    public List<MajorWithSimpleTopic> findAllWithTopicIds() {
        return majorRepository.findBy(MajorWithSimpleTopic.class);
    }

    @Override
    public MajorDTO findById(String id) {
        return new MajorDTO(majorRepository.findById(id).get());
    }

    @Override
    public List<Major> createMajors(List<Major> majors) {
        return majorRepository.saveAll(majors);
    }

    @Override
    @Transactional
    public List<Major> updateMajors(List<Major> majors) {
        
        Set updateId = majors
                .stream()
                .filter(t -> t.getDescription() != null
                            || t.getName() != null)
                .map(Major::getId)
                .collect(Collectors.toSet());

        List<Major> majorList = (List<Major>) majorRepository
                .findAllById(updateId)
                .stream()
                .collect(Collectors.toList());

        majorList.forEach((major -> {
            Major updateMajor = majors
                    .stream()
                    .filter(t-> t.getId().equals(major.getId()))
                    .findAny()
                    .get();
            
            if(updateMajor.getDescription() != null) 
                major.setDescription(updateMajor.getDescription());
            
            if(updateMajor.getName() != null)
                major.setName(updateMajor.getName());
            
        }));

        return majorRepository.saveAll(majorList);
    }

    @Override
    public void deleteMajors(List<String> ids) {
        majorRepository.deleteAllById(ids);
    }
    
}
