/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services.implv1;

import com.lasa.business.config.utils.LecturerStatus;
import com.lasa.business.services.EmailSenderService;
import com.lasa.business.services.LecturerService;
import com.lasa.data.model.entity.Lecturer;
import com.lasa.data.model.entity.LecturerTopicDetail;
import com.lasa.data.model.entity.Topic;
import com.lasa.data.model.request.LecturerRequestModel;
import com.lasa.data.model.utils.criteria.LecturerSearchCriteria;
import com.lasa.data.model.utils.page.LecturerPage;
import com.lasa.data.model.utils.specification.LecturerSpecification;
import com.lasa.data.model.view.LecturerViewModel;
import com.lasa.data.repo.repository.FavoriteLecturerRepository;
import com.lasa.data.repo.repository.LecturerRepository;
import com.lasa.data.repo.repository.LecturerTopicDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Objects;
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
    private final LecturerTopicDetailRepository lecturerTopicDetailRepository;
    private EmailSenderService emailSenderService;

    @Autowired
    public LecturerServiceImpl(LecturerRepository lecturerRepository,
                               FavoriteLecturerRepository favoriteLecturerRepository,
                               LecturerTopicDetailRepository lecturerTopicDetailRepository) {
        this.lecturerRepository = lecturerRepository;
        this.favoriteLecturerRepository = favoriteLecturerRepository;
        this.lecturerTopicDetailRepository = lecturerTopicDetailRepository;
    }

    @Autowired
    public void setEmailSenderService(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
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
    @Transactional
    public LecturerViewModel updateLecturer(LecturerRequestModel model) throws MessagingException {
        
        Lecturer lecturer = lecturerRepository.findById(model.getId()).get();
        
        if(Objects.nonNull(model.getName()))
            lecturer.setName(model.getName());
        
        if(Objects.nonNull(model.getPhone()))
            lecturer.setPhone(model.getPhone());
        
        if(Objects.nonNull(model.getBirthday()))
            lecturer.setBirthday(model.getBirthday());
        
        if(Objects.nonNull(model.getGender()))
            lecturer.setGender(model.getGender());
        
        if(Objects.nonNull(model.getAddress()))
            lecturer.setAddress(model.getAddress());
        
        if(Objects.nonNull(model.getMeetingUrl()))
            lecturer.setMeetingUrl(model.getMeetingUrl());

        if(Objects.nonNull(model.getTopics())) {
            List<LecturerTopicDetail> lecturerTopicDetails = model.getTopics()
                    .stream()
                    .map(t -> LecturerTopicDetail.builder()
                            .lecturer(Lecturer.builder()
                                    .id(model.getId())
                                    .build())
                            .topic(Topic.builder()
                                    .id(t)
                                    .build())
                            .build())
                    .collect(Collectors.toList());

            lecturerTopicDetailRepository.deleteAllByLecturerId(model.getId());
            lecturerTopicDetailRepository.saveAll(lecturerTopicDetails);
        }

        if(Objects.nonNull(model.getStatus())) {
            lecturer.setStatus(model.getStatus());
            if(lecturer.getStatus() == 1) {
                emailSenderService.sendEmailWithAttachment(
                        lecturer.getEmail(),
                        "Dear Mr/Ms " + lecturer.getName()  +
                                "\n" +
                                "\n Your account has been activated." +
                                "\n" +
                                "\n" +
                                "\n Best regards, " +
                                "\n Lasa customer service team.",
                        "Notification Email for Lecturer Signup."
                );
            }
        }
        if(Objects.nonNull(model.getAvatarUrl()))
            lecturer.setAvatarUrl(model.getAvatarUrl());
        
        return new LecturerViewModel(lecturerRepository.save(lecturer));

    }

    @Override
    public boolean verifyLecturer(List<Integer> id) {
        return lecturerRepository.countAvailableForDelete(id) == id.size();
    }

    @Override
    @Transactional
    public void deleteLecturer(List<Integer> id) {
        List<Lecturer> lecturers = lecturerRepository.findAllById(id);
        lecturers.stream()
                .forEach(t -> t.setStatus(LecturerStatus.DELETED.getCode()));
        lecturerRepository.saveAll(lecturers);
    }

}
