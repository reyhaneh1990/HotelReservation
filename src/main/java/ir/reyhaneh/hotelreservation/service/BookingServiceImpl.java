//این خط مشخص می‌کند که کلاس BookingServiceImpl در پکیج ir.reyhaneh.hotelreservation.service قرار دارد.
package ir.reyhaneh.hotelreservation.service;
//این خطوط کتابخانه‌ها و کلاس‌های مورد نیاز را ایمپورت می‌کنند
//کلاس مدل مربوط به رزروها

import ir.reyhaneh.hotelreservation.model.Bookings;
import ir.reyhaneh.hotelreservation.repository.BookingRepository;
import ir.reyhaneh.hotelreservation.repository.PaymentsRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//این annotation از کتابخانه Lombok برای ایجاد خودکار یک کانستراکتور با تمام پارامترها استفاده می‌کند.
@AllArgsConstructor
//این annotation مشخص می‌کند که این کلاس یک سرویس است و توسط اسپرینگ به عنوان یک bean مدیریت می‌شود.
@Service
//این کلاس اینترفیس BookingService را پیاده‌سازی می‌کند و متدهای آن را تعریف می‌کند.
public class BookingServiceImpl implements BookingService {
    //این خطوط وابستگی‌های مورد نیاز کلاس را تعریف می‌کنند
    //سرویس مربوط به مشتریان
    private final CustomerService customerService;
    //سرویس مربوط به اتاق‌ها
    private final RoomService roomService;
    //ریپوزیتوری برای دسترسی به داده‌های رزروها
    private final BookingRepository bookingRepository;
    //ریپوزیتوری برای دسترسی به داده‌های پرداخت‌ها
    private final PaymentsRepository paymentsRepository;

    @Override
    //این متد لیستی از تمام رزروها را از ریپوزیتوری bookingRepository دریافت می‌کند و برمی‌گرداند.
    //کاربرد: برای دریافت تمام رزروهای موجود در سیستم.
    public List<Bookings> getAllBookings() {
        return bookingRepository.getAllBookings();
    }

    @Override
//این متد یک رزرو را بر اساس شناسه (id) آن از ریپوزیتوری bookingRepository دریافت می‌کند و برمی‌گرداند.
//کاربرد: برای دریافت اطلاعات یک رزرو خاص با استفاده از شناسه آن.
    public Bookings getBookingById(Long id) {
        return bookingRepository.getBooking(id);
    }

    @Override
    // این annotation مشخص می‌کند که این متد باید در یک تراکنش اجرا شود.
    @Transactional
    //این متد یک رزرو جدید را ذخیره می‌کند و پرداخت مربوط به آن را ثبت می‌کند.
    public void saveBooking(Long customerId, Long roomId, Date checkInDate, Date checkOutDate, Long amount, String paymentMethod) {
        //بررسی می‌کند که مشتری با شناسه customerId وجود دارد یا خیر. اگر وجود نداشته باشد، یک خطای ValidationException پرتاب می‌شود.
        if (customerService.getCustomerById(customerId) == null) {
            throw new ValidationException("Customer not found");
        }
        //بررسی می‌کند که اتاق با شناسه roomId وجود دارد یا خیر. اگر وجود نداشته باشد، یک خطای ValidationException پرتاب می‌شود.
        if (roomService.getRoomById(roomId) == null) {
            throw new ValidationException("Room not found");
        }
        //بررسی می‌کند که تاریخ خروج (checkOutDate) قبل از تاریخ ورود (checkInDate) نباشد. اگر باشد، یک خطای ValidationException پرتاب می‌شود.
        if (checkOutDate.before(checkInDate)) {
            throw new ValidationException("Check Out Date is before Check In Date");
        }
        //بررسی می‌کند که مبلغ پرداختی (amount) معتبر باشد (نه null و نه منفی). اگر معتبر نباشد، یک خطای ValidationException پرتاب می‌شود.
        if (amount == null || amount < 0) {
            throw new ValidationException("Amount is negative");
        }
//یک پرداخت جدید را در ریپوزیتوری paymentsRepository ثبت می‌کند و شناسه پرداخت (paymentId) را دریافت می‌کند.
        Long paymentId = paymentsRepository.addPayments(amount, new Date(), paymentMethod);
//یک رزرو جدید را در ریپوزیتوری bookingRepository ثبت می‌کند و اطلاعات مربوط به رزرو را ذخیره می‌کند.
        bookingRepository.addBookings(new Date(), checkInDate, checkOutDate, customerId, roomId, paymentId);
    }
}
