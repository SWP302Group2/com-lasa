/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.controllers.MajorOperations;
import com.lasa.business.services.MajorService;
import com.lasa.business.services.QuestionService;
import com.lasa.business.services.TopicService;
import com.lasa.data.model.request.MajorRequestModel;
import com.lasa.data.model.utils.criteria.QuestionSearchCriteria;
import com.lasa.data.model.utils.criteria.TopicSearchCriteria;
import com.lasa.data.model.view.MajorViewModel;
import com.lasa.data.model.utils.criteria.MajorSearchCriteria;
import com.lasa.data.model.utils.page.MajorPage;
import com.lasa.data.model.view.QuestionViewModel;
import com.lasa.data.model.view.TopicViewModel;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api/v1/majors")
@Api(value = "majors", description = "For majors", tags = { "Major Controller" })
public class MajorController implements MajorOperations {

    private final MajorService majorService;
    private final TopicService topicService;

    @Autowired
    public MajorController(@Qualifier("MajorServiceImplV1") MajorService majorService,
                           @Qualifier("TopicServiceImplV1") TopicService topicService) {
        this.majorService = majorService;
        this.topicService = topicService;
    }


    @Override
    public ResponseEntity<?> findWithArgument(MajorPage majorPage, MajorSearchCriteria searchCriteria) {
        if (majorPage.isPaging()) {
            Page<MajorViewModel> page = majorService.findAll(majorPage, searchCriteria);
            if (searchCriteria.getGetTopic().equals(true)) {
                List<String> majorIds = new ArrayList<>();
                page.stream()
                        .forEach(t -> majorIds.add(t.getId()));
                List<TopicViewModel> topicViewModels = getTopics(majorIds);
                page.stream()
                        .forEach(t -> {
                            topicViewModels.stream()
                                    .forEach(x -> {
                                        if (x.getMajorId().equals(t.getId()))
                                            t.addTopic(x);
                                    });
                        });
            }
            return ResponseEntity.ok(page);
        } else {
            List<MajorViewModel> list = majorService.findAll(searchCriteria);
            if (searchCriteria.getGetTopic().equals(true)) {
                List<String> majorIds = new ArrayList<>();
                list.stream()
                        .forEach(t -> majorIds.add(t.getId()));
                List<TopicViewModel> topicViewModels = getTopics(majorIds);

                list.stream()
                        .forEach(t -> {
                            topicViewModels.stream()
                                    .forEach(x -> {
                                        if (x.getMajorId().equals(t.getId()))
                                            t.addTopic(x);
                                    });
                        });
            }
            return ResponseEntity.ok(list);
        }
    }

        @Override
        public ResponseEntity<MajorViewModel> findById (String id){
            MajorViewModel viewModel = majorService.findById(id);
            if (Objects.isNull(viewModel))
                return ResponseEntity.ok(null);

            return ResponseEntity.ok(viewModel);
        }

        @Override
        public ResponseEntity<?> findByIdIncludeTopics (String id){
            MajorViewModel majorViewModel = majorService.findById(id);
            if (Objects.isNull(majorViewModel))
                return ResponseEntity.ok(null);
            List<String> majorIds = new ArrayList<>();
            majorIds.add(majorViewModel.getId());

            majorViewModel.setTopics(getTopics(majorIds));

            return ResponseEntity.ok(majorViewModel);
        }

        private List<TopicViewModel> getTopics (List < String > majorIds) {
            return topicService.findWithArgument(TopicSearchCriteria.builder()
                    .majorId(majorIds)
                    .build());
        }

        @Override
        public ResponseEntity<List<MajorViewModel>> createMajors (List < MajorRequestModel > majors) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(majorService.createMajors(majors));
        }

        @Override
        public ResponseEntity<?> updateMajors (List < MajorRequestModel > majors) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(majorService.updateMajors(majors));
        }

        @Override
        public void deleteMajors (List < String > ids) {
            majorService.deleteMajors(ids);
        }

    }

