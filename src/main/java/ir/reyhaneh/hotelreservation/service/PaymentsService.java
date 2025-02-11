//این خط مشخص می‌کند که اینترفیس PaymentsService در پکیج ir.reyhaneh.hotelreservation.service قرار دارد
package ir.reyhaneh.hotelreservation.service;
//این خطوط کتابخانه‌ها و کلاس‌های مورد نیاز را ایمپورت می‌کنند
//کلاس مدل مربوط به پرداخت‌ها

import ir.reyhaneh.hotelreservation.model.Payments;

import java.util.List;

//این خط یک اینترفیس به نام PaymentsService تعریف می‌کند. اینترفیس‌ها در جاوا برای تعریف قراردادهایی (Contract) استفاده می‌شوند که کلاس‌های دیگر باید آن‌ها را پیاده‌سازی کنند.
public interface PaymentsService {
    //این متد یک لیست از تمام پرداخت‌ها (Payments) را برمی‌گرداند.
//کاربرد: برای دریافت تمام پرداخت‌های موجود در سیستم.
    List<Payments> getAllPayments();

    //این متد یک پرداخت (Payments) را بر اساس شناسه (id) آن برمی‌گرداند.
//کاربرد: برای دریافت اطلاعات یک پرداخت خاص با استفاده از شناسه آن.
    Payments getPaymentsById(Long id);

}
