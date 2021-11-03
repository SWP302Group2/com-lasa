package com.lasa.business.services.implv1;

import com.lasa.business.services.BookingRequestService;
import com.lasa.business.services.EmailSenderService;
import com.lasa.data.model.entity.*;
import com.lasa.data.model.request.BookingRequestRequestModel;
import com.lasa.data.model.view.BookingRequestViewModel;
import com.lasa.data.model.utils.criteria.BookingRequestSearchCriteria;
import com.lasa.data.model.utils.page.BookingRequestPage;
import com.lasa.data.model.utils.specification.BookingRequestSpecification;
import com.lasa.data.repo.repository.BookingRequestRepository;
import com.lasa.data.repo.repository.LecturerRepository;
import com.lasa.data.repo.repository.SlotRepository;
import com.lasa.data.repo.repository.StudentRepository;
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
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author wifil
 */

@Component
@Qualifier("BookingRequestServiceImplV1")
public class BookingRequestServiceImpl implements BookingRequestService {

    private final BookingRequestRepository bookingRepository;
    @Autowired
    private  EmailSenderService emailSenderService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private LecturerRepository lecturerRepository;
    @Autowired
    private SlotRepository slotRepository;
    @Autowired
    public BookingRequestServiceImpl(BookingRequestRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Page<BookingRequestViewModel> findAll(BookingRequestPage bookingRequestPage, BookingRequestSearchCriteria searchCriteria) {
        Pageable pageable = PageRequest.of(bookingRequestPage.getPage(), bookingRequestPage.getSize(), Sort.by(bookingRequestPage.getOrderBy(), bookingRequestPage.getSortBy()));
        return bookingRepository
                .findAll(BookingRequestSpecification.searchSpecification(searchCriteria), pageable)
                .map(t -> new BookingRequestViewModel(t));
    }

    @Override
    public List<BookingRequestViewModel> findAll(BookingRequestSearchCriteria searchCriteria) {
        return bookingRepository
                .findAll(BookingRequestSpecification.searchSpecification(searchCriteria))
                .stream()
                .map(t -> new BookingRequestViewModel(t))
                .collect(Collectors.toList());
    }

    @Override
    public BookingRequestViewModel findByBookingRequestId(Integer id) {
        Optional<BookingRequest> bookingRequest = bookingRepository.findById(id);
        if(bookingRequest.isPresent())
            return new BookingRequestViewModel(bookingRequest.get());

        return null;
    }

    @Override
    public Boolean verifyBookingRequest(Integer studentId, Integer slotId) {
        if(bookingRepository.findBookingRequestByStudentIdAndSlotId(studentId, slotId).isPresent())
            return false;
        else
            return true;
    }

    @Override
    public BookingRequestViewModel createBookingRequest(BookingRequestRequestModel bookingRequestModel) {
        BookingRequest bookingRequest = bookingRequestModel.toEntity();
        System.out.println(bookingRequest.getStudentId());
        List<Question> questions = new ArrayList<>();
        bookingRequestModel.getQuestions().stream()
                .forEach(t -> questions.add(t.toEntity(bookingRequest)));
        bookingRequest.setQuestions(questions);

        return new BookingRequestViewModel(bookingRepository.save(bookingRequest));
    }

//    @Transactional
//    @Override
//    public List<BookingRequest> updateBookingRequests(List<BookingRequest> bookingRequests) {
//
//        Set updateId = bookingRequests
//                .stream()
//                .filter(updateBookingRequest -> updateBookingRequest.getStatus() != null)
//                .map(BookingRequest::getId)
//                .collect(Collectors.toSet());
//
//        List<BookingRequest> bookingList = (List<BookingRequest>) bookingRepository
//                .findAllById(updateId)
//                .stream()
//                .collect(Collectors.toList());
//
//        bookingList.forEach((bookingRequest -> {
//            Integer updateStatus = bookingRequests
//                    .stream()
//                    .filter(bookingUpdate -> bookingUpdate
//                    .getId()
//                    .equals(bookingRequest.getId()))
//                    .findAny()
//                    .get()
//                    .getStatus();
//            bookingRequest.setStatus(updateStatus);
//        }));
//
//        return bookingRepository.saveAll(bookingList);
//    }
    @Override
    @Transactional
    public BookingRequestViewModel updateBookingRequest(BookingRequestRequestModel bookingRequestModel) {
        
        BookingRequest updatedBookingRequest = 
                bookingRepository.getById(bookingRequestModel.getId());
        
        if(updatedBookingRequest != null) {
            if(Objects.nonNull(bookingRequestModel.getTopicId()))
                updatedBookingRequest.setTopicId(bookingRequestModel.getTopicId());

            if(Objects.nonNull(bookingRequestModel.getStatus()))
                updatedBookingRequest.setStatus(bookingRequestModel.getStatus());

            if(Objects.nonNull(bookingRequestModel.getTitle()) )
                updatedBookingRequest.setTitle(bookingRequestModel.getTitle());

            if(Objects.nonNull(bookingRequestModel.getRating())) {
                updatedBookingRequest.setId(bookingRequestModel.getRating());
            }
            if(Objects.nonNull(bookingRequestModel.getQuestions()))
                updatedBookingRequest.getQuestions().stream()
                        .forEach(t -> {
                            bookingRequestModel.getQuestions().stream()
                                    .forEach(x -> {
                                        if(x.getId().equals(t.getId()) && !x.getContent().equals(""))
                                            t.setContent(x.getContent());
                                    });
                        });
            return new BookingRequestViewModel(bookingRepository.save(updatedBookingRequest));
        }
        return null;
    }
    

    @Override
    public void deleteBookingRequests(List<Integer> ids) {
        bookingRepository.deleteAllById(ids);
    }

    @Override
    @Scheduled(fixedDelay = 60000L)
    public void announcedMailBeforeMeeting() throws MessagingException {

        /*Slot slot = null;
        Student student = null;
        Lecturer lecturer = null;
        List<BookingRequest> listAnnounced =  bookingRepository.findAllBookingRequestByStatus();
        for (BookingRequest bookingRequest : listAnnounced) {
            slot = slotRepository.findById(bookingRequest.getSlotId()).get();
            student = studentRepository.findById(bookingRequest.getStudentId()).get();
            lecturer = lecturerRepository.findById(slot.getLecturerId()).get();

            LocalDateTime timeStart = slot.getTimeStart();
            LocalDateTime timeEnd = slot.getTimeEnd();

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String timeStartFormat = timeStart.format(timeFormatter);

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String createTimeFormat = bookingRequest.getCreateTime().format(dateFormatter);

            LocalDateTime now = LocalDateTime.now();
            Period period = Period.between(now.toLocalDate(),timeStart.toLocalDate());
            Duration duration = Duration.between(now.toLocalTime(), timeEnd.toLocalTime());

            if (period.toString().equals("P0D") && (duration.getSeconds() == 3600 || duration.getSeconds() == -3600)) {
                    emailSenderService.sendEmailWithAttachment(student.getEmail(),
                                        "Dear Student: " + student.getName() + ", " + student.getMssv() +
                                                "\n Prepare for: " + timeStartFormat +
                                            "\n The host of meeting is:"+ lecturer.getName() +
                                            "\n Hear is link to google meet: "+ lecturer.getMeetingUrl(),

                                    "Remind! You have an appointment created on " + createTimeFormat);

                    bookingRequest.setStatus(3);
                    bookingRepository.save(bookingRequest);
            }
        }*/
    }
}
