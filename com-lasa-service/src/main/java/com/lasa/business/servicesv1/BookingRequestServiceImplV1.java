package com.lasa.business.servicesv1;

import com.lasa.data.entity.BookingRequest;
import com.lasa.data.repository.BookingRequestRepository;
import java.util.List;
import java.util.Optional;

import com.lasa.business.services.BookingRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author wifil
 */

@Component
@Qualifier("BookingRequestServiceImplV1")
public class BookingRequestServiceImplV1 implements BookingRequestService {

    private final BookingRequestRepository bookingRepository;

    @Autowired
    public BookingRequestServiceImplV1(BookingRequestRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<BookingRequest> findAllBookingRequests() {
        return bookingRepository.findAll();
    }

    //@searchValue : student id
    //@sortBy option
    @Override
    public Page<BookingRequest> findPageBookingRequest(Integer page, Integer size) {
            return bookingRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<BookingRequest> findPageBookingRequestByStudentId(Integer page, Integer size, Integer studentId) {
        return bookingRepository.findBookingRequestsByStudentId(studentId , PageRequest.of(page, size));
    }

    @Override
    public Page<BookingRequest> findPageBookingRequestBySlotId(Integer page, Integer size, Integer slotId) {
        return bookingRepository.findBookingRequestsBySlotId(slotId, PageRequest.of(page, size));
    }

    @Override
    public Page<BookingRequest> findPageBookingRequestByStatus(Integer page, Integer size, Integer status) {
        return bookingRepository.findBookingRequestsByStatus(status, PageRequest.of(page,size));
    }


    @Override
    public BookingRequest findBookingRequestAndGetQuestion(Integer id) {
        return bookingRepository.findByIdAndGetQuestions(id).get();
    }

    @Override
    public BookingRequest findByBookingRequestId(Integer id) {
        return bookingRepository.findById(id).get();
    }

    @Override
    public long countBookingRequest() {
        return bookingRepository.count();
    }

    @Override
    public BookingRequest createBookingRequest(BookingRequest bookingRequest) {
        return bookingRepository.save(bookingRequest);
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
    public BookingRequest updateBookingRequest(BookingRequest bookingRequest) {
        
        BookingRequest updatedBookingRequest = 
                bookingRepository.getById(bookingRequest.getId());
        
        if(updatedBookingRequest != null) {
            if(bookingRequest.getTopicId() != null) {
                updatedBookingRequest.setTopicId(bookingRequest.getTopicId());
            }
            if(bookingRequest.getStatus() != null) {
                updatedBookingRequest.setStatus(bookingRequest.getStatus());
            }
            return bookingRepository.save(updatedBookingRequest);
        }
        return null;
    }
    

    @Override
    public void deleteBookingRequests(List<Integer> ids) {
        bookingRepository.deleteAllById(ids);
    }

}
