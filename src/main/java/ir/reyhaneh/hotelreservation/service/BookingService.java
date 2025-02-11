package ir.reyhaneh.hotelreservation.service;

import ir.reyhaneh.hotelreservation.model.Bookings;

import java.util.Date;
import java.util.List;

public interface BookingService {

    List<Bookings> getAllBookings();

    Bookings getBookingById(Long id);

    void saveBooking(Long customerId, Long roomId, Date checkInDate, Date checkOutDate, Long amount, String paymentMethod);

}
