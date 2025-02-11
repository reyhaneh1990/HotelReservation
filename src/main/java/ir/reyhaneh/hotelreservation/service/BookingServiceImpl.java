package ir.reyhaneh.hotelreservation.service;

import ir.reyhaneh.hotelreservation.model.Bookings;
import ir.reyhaneh.hotelreservation.repository.BookingRepository;
import ir.reyhaneh.hotelreservation.repository.PaymentsRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

    private final CustomerService customerService;
    private final RoomService roomService;
    private final BookingRepository bookingRepository;
    private final PaymentsRepository paymentsRepository;

    @Override
    public List<Bookings> getAllBookings() {
        return bookingRepository.getAllBookings();
    }


    @Override
    public Bookings getBookingById(Long id) {
        return bookingRepository.getBooking(id);
    }

    @Override
    @Transactional
    public void saveBooking(Long customerId, Long roomId, Date checkInDate, Date checkOutDate, Long amount, String paymentMethod) {
        if (customerService.getCustomerById(customerId) == null) {
            throw new ValidationException("Customer not found");
        }
        if (roomService.getRoomById(roomId) == null) {
            throw new ValidationException("Room not found");
        }
        if (checkOutDate.before(checkInDate)) {
            throw new ValidationException("Check Out Date is before Check In Date");
        }
        if (amount == null || amount < 0) {
            throw new ValidationException("Amount is negative");
        }

        Long paymentId = paymentsRepository.addPayments(amount, new Date(), paymentMethod);

        bookingRepository.addBookings(new Date(), checkInDate, checkOutDate, customerId, roomId, paymentId);
    }
}
