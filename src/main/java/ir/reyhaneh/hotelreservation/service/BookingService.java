//این خط مشخص می‌کند که اینترفیس BookingService در پکیج ir.reyhaneh.hotelreservation.service قرار دارد.
package ir.reyhaneh.hotelreservation.service;
//این خطوط کتابخانه‌ها و کلاس‌های مورد نیاز را ایمپورت می‌کنند
//کلاس مدل مربوط به رزروها

import ir.reyhaneh.hotelreservation.model.Bookings;

import java.util.Date;
import java.util.List;

//این خط یک اینترفیس به نام BookingService تعریف می‌کند. اینترفیس‌ها در جاوا برای تعریف قراردادهایی (Contract) استفاده می‌شوند که کلاس‌های دیگر باید آن‌ها را پیاده‌سازی کنند.
public interface BookingService {
    //این متد یک لیست از تمام رزروها (Bookings) را برمی‌گرداند.
//کاربرد: برای دریافت تمام رزروهای موجود در سیستم.
    List<Bookings> getAllBookings();
    //  این متد یک رزرو (Bookings) را بر اساس شناسه (id) آن برمی‌گرداند.

    //  کاربرد: برای دریافت اطلاعات یک رزرو خاص با استفاده از شناسه آن.
    Bookings getBookingById(Long id);

    //این متد یک رزرو جدید را ذخیره می‌کند.
//
//پارامترها:
//
//customerId: شناسه مشتری که رزرو را انجام داده است.
//
//roomId: شناسه اتاقی که رزرو شده است.
//
//checkInDate: تاریخ ورود به هتل.
//
//checkOutDate: تاریخ خروج از هتل.
//
//amount: مبلغ پرداختی برای رزرو.
//
//paymentMethod: روش پرداخت (مانند کارت اعتباری، نقدی و غیره).
//
//کاربرد: برای ایجاد و ذخیره یک رزرو جدید در سیستم.
    void saveBooking(Long customerId, Long roomId, Date checkInDate, Date checkOutDate, Long amount, String paymentMethod);

}
