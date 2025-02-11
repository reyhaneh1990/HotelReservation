//این خط مشخص می‌کند که کلاس PaymentsServiceImpl در پکیج ir.reyhaneh.hotelreservation.service قرار دارد.
package ir.reyhaneh.hotelreservation.service;
//این خطوط کتابخانه‌ها و کلاس‌های مورد نیاز را ایمپورت می‌کنند
//کلاس مدل مربوط به پرداخت‌ها.

import ir.reyhaneh.hotelreservation.model.Payments;
import ir.reyhaneh.hotelreservation.repository.PaymentsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//این annotation مشخص می‌کند که این کلاس یک سرویس است و توسط اسپرینگ به عنوان یک bean مدیریت می‌شود.
@Service
//این annotation از کتابخانه Lombok برای ایجاد خودکار یک کانستراکتور با تمام پارامترها استفاده می‌کند.
@AllArgsConstructor
//این کلاس اینترفیس PaymentsService را پیاده‌سازی می‌کند و متدهای آن را تعریف می‌کند.
public class PaymentsServiceImpl implements PaymentsService {
    //ریپوزیتوری برای دسترسی به داده‌های پرداخت‌ها
    private final PaymentsRepository paymentsRepository;

    @Override
    //این متد لیستی از تمام پرداخت‌ها را از ریپوزیتوری paymentsRepository دریافت می‌کند و برمی‌گرداند.
    //کاربرد: برای دریافت تمام پرداخت‌های موجود در سیستم
    public List<Payments> getAllPayments() {
        return paymentsRepository.getAllPayments();
    }


    @Override
    //این متد یک پرداخت را بر اساس شناسه (id) آن از ریپوزیتوری paymentsRepository دریافت می‌کند و برمی‌گرداند.
    //کاربرد: برای دریافت اطلاعات یک پرداخت خاص با استفاده از شناسه آن
    public Payments getPaymentsById(Long id) {
        return paymentsRepository.getPayments(id);
    }

}

