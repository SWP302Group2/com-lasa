/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services.implv1;

import com.lasa.business.services.LecturerService;
import com.lasa.data.model.request.LecturerRequestModel;
import com.lasa.data.model.view.LecturerViewModel;
import com.lasa.data.model.entity.Lecturer;
import com.lasa.data.model.utils.criteria.LecturerSearchCriteria;
import com.lasa.data.model.utils.page.LecturerPage;
import com.lasa.data.model.utils.specification.LecturerSpecification;
import com.lasa.data.repo.repository.FavoriteLecturerRepository;
import com.lasa.data.repo.repository.LecturerRepository;
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
@Qualifier("LecturerServiceImplV1")
public class LecturerServiceImpl implements LecturerService {

    private final LecturerRepository lecturerRepository;
    private final FavoriteLecturerRepository favoriteLecturerRepository;

    @Autowired
    public LecturerServiceImpl(LecturerRepository lecturerRepository,
                               FavoriteLecturerRepository favoriteLecturerRepository) {
        this.lecturerRepository = lecturerRepository;
        this.favoriteLecturerRepository = favoriteLecturerRepository;
    }

    @Override
    public Page<LecturerViewModel> findAll(LecturerPage lecturerPage, LecturerSearchCriteria searchCriteria) {
        Pageable pageable = PageRequest.of(lecturerPage.getPage(), lecturerPage.getSize(), Sort.by(lecturerPage.getOrderBy(), lecturerPage.getSortBy()));
        return lecturerRepository
                .findAll(LecturerSpecification.searchSpecification(searchCriteria), pageable)
                .map(t -> new LecturerViewModel(t));
    }

    @Override
    public List<LecturerViewModel> findAll(LecturerSearchCriteria searchCriteria) {
        return lecturerRepository
                .findAll(LecturerSpecification.searchSpecification(searchCriteria))
                .stream()
                .map(t -> new LecturerViewModel(t))
                .collect(Collectors.toList());
    }

    @Override
    public LecturerViewModel createLecturer(LecturerRequestModel model) {
        Lecturer lecturer = model.toEntity();
        return new LecturerViewModel(lecturerRepository.save(lecturer));
    }

    @Override
    public LecturerViewModel findLecturerById(Integer id) {
        Optional<Lecturer> lecturer = lecturerRepository.findById(id);
        if(lecturer.isPresent())
            return new LecturerViewModel(lecturer.get());
        return null;

    }

    @Override
    public LecturerViewModel updateLecturer(LecturerRequestModel model) {
        
        Lecturer lecturer = lecturerRepository.findById(model.getId()).get();
        
        if(model.getName() != null)
            lecturer.setName(model.getName());
        
        if(model.getPhone() != null)
            lecturer.setPhone(model.getPhone());
        
        if(model.getBirthday() != null)
            lecturer.setBirthday(model.getBirthday());
        
        if(model.getGender() != null)
            lecturer.setGender(model.getGender());
        
        if(model.getAddress() != null)
            lecturer.setAddress(model.getAddress());
        
        if(model.getMeetingUrl() != null)
            lecturer.setMeetingUrl(model.getMeetingUrl());
        
        return new LecturerViewModel(lecturerRepository.save(lecturer));

    }

}
