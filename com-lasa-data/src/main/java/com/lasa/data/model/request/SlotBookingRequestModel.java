package com.lasa.data.model.request;

import com.lasa.data.validator.ValidAcceptOrDenyBooking;
import com.lasa.data.validator.ValidOneOf;
import com.lasa.data.validator.group.PutValidator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ValidAcceptOrDenyBooking(message = "BOOKING_REQUEST_NOT_AVAILABLE_FOR_UPDATE", groups = PutValidator.class)
public class SlotBookingRequestModel {
    private Integer slotId;
    private Integer bookingId;
    @ValidOneOf(value = {-1,2})
    private Integer status;
    private Integer lecturerId;
}
