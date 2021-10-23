package com.lasa.business.services.implv1;

import com.lasa.business.services.BookingRequestService;
import com.lasa.data.dto.BookingRequestDTO;
import com.lasa.data.entity.BookingRequest;
import com.lasa.data.entity.utils.criteria.BookingRequestSearchCriteria;
import com.lasa.data.entity.utils.page.BookingRequestPage;
import com.lasa.data.entity.utils.specification.BookingRequestSpecification;
import com.lasa.data.repo.repository.BookingRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public Page<BookingRequestDTO> findAll(BookingRequestPage bookingRequestPage, BookingRequestSearchCriteria searchCriteria) {
        Pageable pageable = PageRequest.of(bookingRequestPage.getPage(), bookingRequestPage.getSize(), Sort.by(bookingRequestPage.getOrderBy(), bookingRequestPage.getSortBy()));
        return bookingRepository
                .findAll(BookingRequestSpecification.searchSpecification(searchCriteria), pageable)
                .map(t -> new BookingRequestDTO(t));
    }

    @Override
    public List<BookingRequestDTO> findAll(BookingRequestSearchCriteria searchCriteria) {
        return bookingRepository
                .findAll(BookingRequestSpecification.searchSpecification(searchCriteria))
                .stream()
                .map(t -> new BookingRequestDTO(t))
                .collect(Collectors.toList());
    }

    @Override
    public BookingRequestDTO findByBookingRequestId(Integer id) {
        return new BookingRequestDTO(bookingRepository.findById(id).get());
    }

    @Override
    public Boolean verifyBookingRequest(Integer studentId, Integer slotId) {
        if(bookingRepository.findBookingRequestByStudentIdAndSlotId(studentId, slotId).isPresent())
            return false;
        else
            return true;
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
