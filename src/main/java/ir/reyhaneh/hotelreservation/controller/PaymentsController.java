//این خط مشخص می‌کند که این کلاس در پکیج ir.reyhaneh.hotelreservation.controller قرار دارد.
package ir.reyhaneh.hotelreservation.controller;
//این بخش، کلاس‌ها و کتابخانه‌های مورد نیاز را import می‌کند:
//Payments: مدلی است که داده‌های مربوط به پرداخت‌ها را نشان می‌دهد.

import ir.reyhaneh.hotelreservation.model.Payments;
import ir.reyhaneh.hotelreservation.service.PaymentsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//یک سازنده (constructor) ایجاد می‌کند که تمام فیلدهای کلاس را به عنوان پارامتر می‌گیرد. در اینجا، PaymentsService به عنوان پارامتر به کنترلر تزریق می‌شود
@AllArgsConstructor
//مشخص می‌کند که این کلاس یک کنترلر RESTful است و تمام خروجی‌ها به صورت JSON یا XML برگردانده می‌شوند.
@RestController
//این annotation مشخص می‌کند که تمام endpointهای این کنترلر با پیشوند /payments/ شروع می‌شوند.
@RequestMapping(path = "/payments/")
public class PaymentsController {
    //این فیلد یک نمونه از PaymentsService است که برای انجام عملیات مربوط به پرداخت‌ها استفاده می‌شود. این فیلد از طریق سازنده (constructor) به کنترلر تزریق می‌شود
    private final PaymentsService paymentsService;

    //این annotation مشخص می‌کند که این متد به درخواست‌های GET پاسخ می‌دهد.
    @GetMapping("/")
    //این متد تمام پرداخت‌ها را از طریق paymentsService.getAllPayments() دریافت می‌کند و به صورت لیستی از شیء‌های Payments برمی‌گرداند.
    public List<Payments> getAllPayments() {
        return paymentsService.getAllPayments();
    }

    //این متد به درخواست‌های GET با یک پارامتر id پاسخ می‌دهد.
    @GetMapping("{id}")
    //این annotation مقدار id را از URL دریافت می‌کند و به عنوان پارامتر به متد پاس می‌دهد.
    //این متد اطلاعات یک پرداخت خاص را بر اساس id از طریق paymentsService.getPaymentsById(id) دریافت می‌کند
    public Payments getPaymentsById(@PathVariable Long id) {
        return paymentsService.getPaymentsById(id);
    }

}
