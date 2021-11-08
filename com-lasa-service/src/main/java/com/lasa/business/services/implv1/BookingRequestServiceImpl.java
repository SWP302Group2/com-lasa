package com.lasa.business.services.implv1;

import com.lasa.business.config.utils.BookingRequestStatus;
import com.lasa.business.services.BookingRequestService;
import com.lasa.business.services.StudentService;
import com.lasa.data.model.entity.BookingRequest;
import com.lasa.data.model.entity.Question;
import com.lasa.data.model.request.BookingRequestRequestModel;
import com.lasa.data.model.utils.criteria.BookingRequestSearchCriteria;
import com.lasa.data.model.utils.page.BookingRequestPage;
import com.lasa.data.model.utils.specification.BookingRequestSpecification;
import com.lasa.data.model.view.BookingRequestViewModel;
import com.lasa.data.model.view.StudentViewModel;
import com.lasa.data.repo.repository.BookingRequestRepository;
import com.lasa.data.repo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    private final StudentService studentService;
    private final StudentRepository studentRepository;

    @Autowired
    public BookingRequestServiceImpl(BookingRequestRepository bookingRepository, StudentService studentService, StudentRepository studentRepository) {
        this.bookingRepository = bookingRepository;
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @Override
    public Page<BookingRequestViewModel> findAll(BookingRequestPage bookingRequestPage, BookingRequestSearchCriteria searchCriteria) {
        Pageable pageable = PageRequest.of(bookingRequestPage.getPage(), bookingRequestPage.getSize(), Sort.by(bookingRequestPage.getOrderBy(), bookingRequestPage.getSortBy()));
        Page<BookingRequestViewModel> page = bookingRepository
                .findAll(BookingRequestSpecification.searchSpecification(searchCriteria), pageable)
                .map(t -> new BookingRequestViewModel(t));

        if(searchCriteria.getGetStudent().equals(true)) {
            List<Integer> studentIds = page.stream()
                    .map(t -> t.getStudentId())
                    .collect(Collectors.toList());

            List<StudentViewModel> students = getStudents(studentIds);
            page.stream()
                    .forEach(t -> {
                        StudentViewModel student = students.stream()
                                .filter(x -> x.getId().equals(t.getStudentId()))
                                .findAny()
                                .get();
                        t.setStudent(student);
                    });
        }

        return page;
    }

    @Override
    public List<BookingRequestViewModel> findAll(BookingRequestSearchCriteria searchCriteria) {
        List<BookingRequestViewModel> list = bookingRepository
                .findAll(BookingRequestSpecification.searchSpecification(searchCriteria))
                .stream()
                .map(t -> new BookingRequestViewModel(t))
                .collect(Collectors.toList());

        if(searchCriteria.getGetStudent().equals(true)) {
            List<Integer> studentIds = list.stream()
                    .map(t -> t.getStudentId())
                    .collect(Collectors.toList());

            List<StudentViewModel> students = getStudents(studentIds);
            list.stream()
                    .forEach(t -> {
                        StudentViewModel student = students.stream()
                                .filter(x -> x.getId().equals(t.getStudentId()))
                                .findAny()
                                .get();
                        t.setStudent(student);
                    });
        }

        return list;
    }

    @Override
    public BookingRequestViewModel findByBookingRequestId(Integer id) {
        Optional<BookingRequest> bookingRequest = bookingRepository.findById(id);
        if(bookingRequest.isPresent())
            return new BookingRequestViewModel(bookingRequest.get());

        return null;
    }

    @Override
    public Boolean verifyBookingRequestForDelete(Integer studentId, List<Integer> slotId) {
        if(bookingRepository.countAvailableBookingForDelete(studentId, slotId) == slotId.size())
            return true;

        return false;
    }

    @Override
    public BookingRequestViewModel createBookingRequest(BookingRequestRequestModel bookingRequestModel) {
        BookingRequest bookingRequest = bookingRequestModel.toEntity();
        List<Question> questions = new ArrayList<>();
            bookingRequestModel.getQuestions().stream()
                .forEach(t -> questions.add(t.toEntity(bookingRequest)));
        bookingRequest.setQuestions(questions);

        return new BookingRequestViewModel(bookingRepository.save(bookingRequest));
    }

    private List<StudentViewModel> getStudents(List<Integer> ids) {
        return studentRepository.findAllById(ids).stream()
                .map(t -> new StudentViewModel(t))
                .collect(Collectors.toList());
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
        bookingRepository.findAllById(ids)
                .stream()
                .forEach(t -> t.setStatus(BookingRequestStatus.DELETED.getCode()));
    }

}
