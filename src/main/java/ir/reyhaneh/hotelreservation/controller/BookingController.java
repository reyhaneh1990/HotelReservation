//این بخش مشخص می‌کند که این کلاس در چه پکیجی قرار دارد و چه کلاس‌ها و کتابخانه‌هایی را استفاده می‌کند.
package ir.reyhaneh.hotelreservation.controller;
// یک مدل (Entity) است که اطلاعات رزروها را نگهداری می‌کند.

import ir.reyhaneh.hotelreservation.model.Bookings;
import ir.reyhaneh.hotelreservation.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

// یک سازنده ایجاد می‌کند که BookingService را به عنوان وابستگی (Dependency) تزریق می‌کند.
@AllArgsConstructor
// مشخص می‌کند که این کلاس یک کنترلر است و خروجی آن به صورت JSON یا XML به کلاینت ارسال می‌شود.
@RestController
// آدرس پایه (Base URL) برای تمام APIهای این کنترلر را تعیین می‌کند. در اینجا /booking/ است.
@RequestMapping(name = "/booking/")
public class BookingController {
    //این خط یک نمونه از BookingService را به عنوان وابستگی به کنترلر تزریق می‌کند.
    private final BookingService bookingService;

    //این متد با یک درخواست GET به آدرس /booking/ فراخوانی می‌شود
    @GetMapping
    //تمام رزروهای موجود را از طریق bookingService.getAllBookings() دریافت می‌کند و به کلاینت برمی‌گرداند.

    public List<Bookings> getAllBooking() {
        return bookingService.getAllBookings();
    }

    //این متد با یک درخواست GET به آدرس /booking/{id} فراخوانی می‌شود.
    @GetMapping("{id}")
    //یک متغیر مسیر (Path Variable) است که شناسه‌ی رزرو را مشخص می‌کند.
    public Bookings getBookingById(@PathVariable Long id) {
        //رزرو مربوط به آن شناسه را از طریق bookingService.getBookingById(id) دریافت می‌کند و به کلاینت برمی‌گرداند.
        return bookingService.getBookingById(id);
    }

    //این متد با یک درخواست POST به آدرس /booking/{customerId}/{roomId}/{checkInDate}/{checkOutDate}/{amount}/{paymentMethod} فراخوانی می‌شود.
    @PostMapping("{customerId}/{roomId}/{checkInDate}/{checkOutDate}/{amount}/{paymentMethod}")
    //  تمام پارامترها از طریق مسیر (Path) دریافت می‌شوند.
    public void saveBooking(@PathVariable Long customerId, @PathVariable Long roomId, @PathVariable Date checkInDate, @PathVariable Date checkOutDate, @PathVariable Long amount, @PathVariable String paymentMethod) {
        //سپس این اطلاعات به bookingService.saveBooking() ارسال می‌شود تا رزرو جدید ذخیره شود.
        bookingService.saveBooking(customerId, roomId, checkInDate, checkOutDate, amount, paymentMethod);
    }


}
