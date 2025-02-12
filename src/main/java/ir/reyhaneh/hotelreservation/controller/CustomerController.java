//این خط مشخص می‌کند که این کلاس در پکیج ir.reyhaneh.hotelreservation.controller قرار دارد.
package ir.reyhaneh.hotelreservation.controller;
//این بخش کتابخانه‌ها و کلاس‌های مورد نیاز را import می‌کند.
//Customers مدلی است که داده‌های مشتری را نشان می‌دهد.

import ir.reyhaneh.hotelreservation.model.Customers;
import ir.reyhaneh.hotelreservation.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//یک سازنده ایجاد می‌کند که تمام فیلدهای کلاس را به عنوان پارامتر می‌گیرد.
@AllArgsConstructor
//مشخص می‌کند که این کلاس یک کنترلر RESTful است و تمام خروجی‌ها به صورت JSON یا XML برگردانده می‌شوند.
@RestController
//: این annotation مشخص می‌کند که تمام endpointهای این کنترلر با پیشوند /customer/ شروع می‌شوند.
@RequestMapping(path = "/customer/")

public class CustomerController {
    //این فیلد یک نمونه از CustomerService است که برای انجام عملیات مربوط به مشتریان استفاده می‌شود.
    private final CustomerService customerService;

    //این annotation مشخص می‌کند که این متد به درخواست‌های GET پاسخ می‌دهد.
    @GetMapping("/")
    //این متد تمام مشتریان را از طریق customerService.getAllCustomers() دریافت می‌کند و به صورت لیستی از شیء‌های Customers برمی‌گرداند.
    public List<Customers> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    //این متد به درخواست‌های GET با یک پارامتر id پاسخ می‌دهد.
    @GetMapping("{id}")
    // این annotation مقدار id را از URL دریافت می‌کند و به عنوان پارامتر به متد پاس می‌دهد.
    //این متد اطلاعات یک مشتری خاص را بر اساس id از طریق customerService.getCustomerById(id) دریافت می‌کند.
    public Customers getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    //این متد به درخواست‌های POST پاسخ می‌دهد و پارامترهای name, email, phone, و address را از URL دریافت می‌کند.
    @PostMapping("{name}/{email}/{phone}/{address}")
    //این متد یک مشتری جدید را از طریق customerService.saveCustomer(name, email, phone, address) ذخیره می‌کند.
    public void saveCustomer(@PathVariable String name, @PathVariable String email, @PathVariable String phone, @PathVariable String address) {
        customerService.saveCustomer(name, email, phone, address);
    }

    //این متد به درخواست‌های PUT پاسخ می‌دهد و پارامترهای id, name, email, phone, و address را از URL دریافت می‌کند.
    @PutMapping("{id}/{name}/{email}/{phone}/{address}")
    //این متد اطلاعات یک مشتری موجود را بر اساس id از طریق customerService.updateCustomer(id, name, email, phone, address) به‌روزرسانی می‌کند.
    public void updateCustomer(@PathVariable Long id, @PathVariable String name, @PathVariable String email, @PathVariable String phone, @PathVariable String address) {
        customerService.updateCustomer(id, name, email, phone, address);
    }

    //این متد به درخواست‌های DELETE پاسخ می‌دهد و پارامتر id را از URL دریافت می‌کند
    @DeleteMapping("delete/{id}")
    //این متد یک مشتری را بر اساس id از طریق customerService.deleteCustomer(id) حذف می‌کند.
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }


}
