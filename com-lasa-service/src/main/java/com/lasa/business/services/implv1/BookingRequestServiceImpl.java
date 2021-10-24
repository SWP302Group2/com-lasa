package com.lasa.business.services.implv1;

import com.lasa.business.services.BookingRequestService;
import com.lasa.data.model.entity.Question;
import com.lasa.data.model.request.BookingRequestRequestModel;
import com.lasa.data.model.view.BookingRequestViewModel;
import com.lasa.data.model.entity.BookingRequest;
import com.lasa.data.model.utils.criteria.BookingRequestSearchCriteria;
import com.lasa.data.model.utils.page.BookingRequestPage;
import com.lasa.data.model.utils.specification.BookingRequestSpecification;
import com.lasa.data.repo.repository.BookingRequestRepository;
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
            if(bookingRequestModel.getTopicId() != null) {
                updatedBookingRequest.setTopicId(bookingRequestModel.getTopicId());
            }
            if(bookingRequestModel.getStatus() != null) {
                updatedBookingRequest.setStatus(bookingRequestModel.getStatus());
            }
            return new BookingRequestViewModel(bookingRepository.save(updatedBookingRequest));
        }
        return null;
    }
    

    @Override
    public void deleteBookingRequests(List<Integer> ids) {
        bookingRepository.deleteAllById(ids);
    }

}
