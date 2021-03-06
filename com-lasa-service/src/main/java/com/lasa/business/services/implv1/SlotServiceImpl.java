/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services.implv1;

import com.lasa.business.config.utils.BookingRequestStatus;
import com.lasa.business.config.utils.SlotStatus;
import com.lasa.business.services.EmailSenderService;
import com.lasa.business.services.LecturerService;
import com.lasa.business.services.SlotService;
import com.lasa.business.services.SlotTopicDetailService;
import com.lasa.data.model.entity.BookingRequest;
import com.lasa.data.model.entity.Slot;
import com.lasa.data.model.request.SlotBookingRequestModel;
import com.lasa.data.model.request.SlotRequestModel;
import com.lasa.data.model.request.SlotTopicDetailRequestModel;
import com.lasa.data.model.utils.criteria.BookingRequestSearchCriteria;
import com.lasa.data.model.utils.criteria.LecturerSearchCriteria;
import com.lasa.data.model.utils.criteria.SlotSearchCriteria;
import com.lasa.data.model.utils.criteria.SlotTopicDetailSearchCriteria;
import com.lasa.data.model.utils.page.SlotPage;
import com.lasa.data.model.utils.specification.BookingRequestSpecification;
import com.lasa.data.model.utils.specification.SlotSpecification;
import com.lasa.data.model.view.LecturerViewModel;
import com.lasa.data.model.view.SlotTopicDetailViewModel;
import com.lasa.data.model.view.SlotViewModel;
import com.lasa.data.repo.repository.BookingRequestRepository;
import com.lasa.data.repo.repository.SlotRepository;
import com.lasa.data.repo.repository.SlotTopicDetailRepository;
import com.lasa.security.utils.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    private final Logger LOGGER = LogManager.getLogger(SlotServiceImpl.class);
    private final SlotRepository slotRepository ;
    private final SlotTopicDetailService slotTopicDetailService;
    private final LecturerService lecturerService;
    private EmailSenderService emailSenderService;
    private final BookingRequestRepository bookingRequestRepository;
    private final SlotTopicDetailRepository slotTopicDetailRepository;

    @Autowired
    public SlotServiceImpl(
            SlotRepository slotRepository,
            @Qualifier("LecturerServiceImplV1") LecturerService lecturerService,
            @Qualifier("SlotTopicDetailServiceImplV1") SlotTopicDetailService slotTopicDetailService,
            BookingRequestRepository bookingRequestRepository,
            SlotTopicDetailRepository slotTopicDetailRepository) {
        this.slotRepository = slotRepository;
        this.lecturerService = lecturerService;
        this.slotTopicDetailService = slotTopicDetailService;
        this.bookingRequestRepository = bookingRequestRepository;
        this.slotTopicDetailRepository = slotTopicDetailRepository;
    }

    @Autowired
    public void setEmailSenderService(@Qualifier("EmailSenderServiceImplV1") EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
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
    public Boolean verifySlotForDelete(List<Integer> id, Integer lecturerId) {
        return slotRepository.countAvailableDeleteSlot(id, lecturerId) == id.size();
    }

    @Override
    public SlotViewModel createSlot(SlotRequestModel model) {
        return new SlotViewModel(slotRepository.save(model.toEntity()));
    }

    @Override
    @Transactional
    public SlotViewModel updateSlots(SlotRequestModel slotRequestModel) {
        Slot slot = slotRepository.findById(slotRequestModel.getId()).get();

        if(Objects.nonNull(slotRequestModel.getTopics())) {
            slotTopicDetailRepository.deleteAllBySlotId(slot.getId());

            List<SlotTopicDetailRequestModel> slotTopicDetailRequestModels = slotRequestModel.getTopics()
                                    .stream()
                                    .map(t -> {
                                        return SlotTopicDetailRequestModel.builder()
                                                .topicId(t)
                                                .slotId(slotRequestModel.getId())
                                                .build();
                                    }).collect(Collectors.toList());
            slotTopicDetailService.createSlotTopicDetails(slotTopicDetailRequestModels);
        }

        if(Objects.nonNull(slotRequestModel.getStatus())) {
            slot.setStatus(slotRequestModel.getStatus());
            if(slot.getStatus() == SlotStatus.CANCELED.getCode())
                //if slot status = 0 => denied all booking
                slot.getBookingRequests().forEach(t -> t.setStatus(BookingRequestStatus.DENIED.getCode()));
        }

        return new SlotViewModel(slotRepository.save(slot));

    }

    @Override
    @Transactional
    public SlotViewModel acceptDenyBooking(SlotBookingRequestModel model) {
        Slot slot = slotRepository.findById(model.getSlotId()).get();
        if(model.getStatus().equals(SlotStatus.ACCEPTED.getCode())) {
            slot.setStatus(SlotStatus.ACCEPTED.getCode());
            List<BookingRequest> bookingRequests = new ArrayList<>(slot.getBookingRequests());
            bookingRequests.forEach(t -> {
                if(t.getId().equals(model.getBookingId())) {
                    t.setStatus(BookingRequestStatus.ACCEPTED.getCode());
                    try {
                        emailSenderService.sendEmailAfterBookingAccepted(slot , t);
                    } catch (MessagingException e) {
                        throw new ExceptionUtils.EmailSenderException("EMAIL_SENDER_ERROR");
                    }
                }
                else
                    t.setStatus(-1);
            });
        }
        if(model.getStatus().equals(-1)) {
            List<BookingRequest> bookingRequests = new ArrayList<>(slot.getBookingRequests());
            bookingRequests.stream().forEach(t -> {
                if(t.getId().equals(model.getBookingId()))
                    t.setStatus(-1);
            });
        }

        return new SlotViewModel(slotRepository.save(slot));
    }

    @Override
    @Transactional
    public void deleteSlots(List<Integer> ids) {
        List<Slot> slots = slotRepository.findAllById(ids);
        slots.stream()
                .forEach(t -> t.setStatus(SlotStatus.DELETED.getCode()));
        slotRepository.saveAll(slots);
    }

    @Override
    @Scheduled(fixedDelay = 1000000L)
    @Transactional
    public void updateStatusForCompletedSlotAndBooking() {
        LOGGER.info("updateStatusForCompletedSlotAndBooking");
        SlotSearchCriteria slotSearchCriteria = SlotSearchCriteria.builder()
                .getLecturer(false)
                .getTopic(false)
                .timeEnd(LocalDateTime.now().minusMinutes(30))
                .status(SlotStatus.NOTIFIED.getCode())
                .build();

        List<Slot> slot = slotRepository.findAll(SlotSpecification.searchSpecification(slotSearchCriteria));
        if(!slot.isEmpty()) {

            slot.stream().forEach(t -> t.setStatus(SlotStatus.COMPLETED.getCode()));
            slotRepository.saveAll(slot);

            BookingRequestSearchCriteria bookingRequestSearchCriteria = BookingRequestSearchCriteria.builder()
                    .slotId(slot.stream()
                            .map(t -> t.getId())
                            .collect(Collectors.toList()))
                    .getStudent(false)
                    .status(BookingRequestStatus.NOTIFIED.getCode())
                    .build();

            List<BookingRequest> bookingRequests = bookingRequestRepository.findAll(BookingRequestSpecification.searchSpecification(bookingRequestSearchCriteria));
            bookingRequests.stream()
                    .forEach(t -> t.setStatus(BookingRequestStatus.COMPLETED.getCode()));
            bookingRequestRepository.saveAll(bookingRequests);
        }

    }

    @Override
    @Scheduled(initialDelay= 50000L, fixedDelay = 100000L)
    @Transactional
    public void updateStatusForExpiredSlotAndBooking() {
        LOGGER.info("updateStatusForExpiredSlotAndBooking");
        SlotSearchCriteria slotSearchCriteria = SlotSearchCriteria.builder()
                .getLecturer(false)
                .getTopic(false)
                .timeEnd(LocalDateTime.now().minusMinutes(1))
                .status(SlotStatus.CREATED.getCode())
                .build();
        System.out.println(LocalDateTime.now());

        List<Slot> expiredSlot = slotRepository.findAll(SlotSpecification.searchSpecification(slotSearchCriteria));
        if (!expiredSlot.isEmpty()) {
            expiredSlot.stream()
                    .forEach(t -> t.setStatus(0));
            slotRepository.saveAll(expiredSlot);

            BookingRequestSearchCriteria bookingRequestSearchCriteria = BookingRequestSearchCriteria.builder()
                    .status(BookingRequestStatus.CREATED.getCode())
                    .slotId(expiredSlot.stream()
                            .map(t -> t.getId())
                            .collect(Collectors.toList()))
                    .getStudent(false)
                    .build();

            List<BookingRequest> bookingRequests = bookingRequestRepository.findAll(BookingRequestSpecification.searchSpecification(bookingRequestSearchCriteria));
            bookingRequests.stream()
                    .forEach(t -> t.setStatus(0));
            bookingRequestRepository.saveAll(bookingRequests);
        }
    }


}
