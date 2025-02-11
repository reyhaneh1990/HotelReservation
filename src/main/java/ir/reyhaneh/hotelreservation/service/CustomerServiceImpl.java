//این خط مشخص می‌کند که کلاس CustomerServiceImpl در پکیج ir.reyhaneh.hotelreservation.service قرار دارد.
package ir.reyhaneh.hotelreservation.service;
//این خطوط کتابخانه‌ها و کلاس‌های مورد نیاز را ایمپورت می‌کنند
//کلاس مدل مربوط به مشتریان

import ir.reyhaneh.hotelreservation.model.Customers;
import ir.reyhaneh.hotelreservation.repository.CustomerRepository;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//این annotation مشخص می‌کند که این کلاس یک سرویس است و توسط اسپرینگ به عنوان یک bean مدیریت می‌شود.
@Service
//این annotation از کتابخانه Lombok برای ایجاد خودکار یک کانستراکتور با تمام پارامترها استفاده می‌کند.
@AllArgsConstructor
//این کلاس اینترفیس CustomerService را پیاده‌سازی می‌کند و متدهای آن را تعریف می‌کند.
public class CustomerServiceImpl implements CustomerService {
    //این خط وابستگی مورد نیاز کلاس را تعریف می‌کند
    //ریپوزیتوری برای دسترسی به داده‌های مشتریان
    private final CustomerRepository customerRepository;
    @Override
    //این متد لیستی از تمام مشتریان را از ریپوزیتوری customerRepository دریافت می‌کند و برمی‌گرداند.
    //کاربرد: برای دریافت تمام مشتریان موجود در سیستم.
    public List<Customers> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    //این متد یک مشتری را بر اساس شناسه (id) آن از ریپوزیتوری customerRepository دریافت می‌کند و برمی‌گرداند.
//کاربرد: برای دریافت اطلاعات یک مشتری خاص با استفاده از شناسه آن.
    @Override
    public Customers getCustomerById(Long id) {
        return customerRepository.getCustomer(id);
    }

    @Override
    //این متد یک مشتری جدید را ذخیره می‌کند
    public void saveCustomer(String name, String email, String phone, String address) {
        //اعتبارسنجی:
        //بررسی می‌کند که نام مشتری (name) خالی نباشد. اگر خالی باشد، یک خطای ValidationException پرتاب می‌شود
        if (name.isEmpty()) {
            throw new ValidationException("Name is empty");
        }

        customerRepository.addCustomer(name, email, phone, address);
    }
//این متد اطلاعات یک مشتری موجود را به‌روزرسانی می‌کند

    @Override
    public void updateCustomer(Long id, String name, String email, String phone, String address) {
        //اعتبارسنجی:
        //بررسی می‌کند که شناسه (id) null نباشد. اگر null باشد، یک خطای ValidationException پرتاب می‌شود.
        if (id == null) {
            throw new ValidationException("id is null");
        }
        if (getCustomerById(id) == null) {
            //بررسی می‌کند که مشتری با شناسه id وجود داشته باشد. اگر وجود نداشته باشد، یک خطای ValidationExce
            throw new ValidationException("Customer is not found");
        }

        customerRepository.editCustomer(id, name, email, phone, address);
    }

    @Override
    //این متد یک مشتری را بر اساس شناسه (id) آن حذف می‌کند
    public void deleteCustomer(Long id) {
        if (id == null) {
            //بررسی می‌کند که شناسه (id) null نباشد. اگر null باشد، یک خطای ValidationException پرتاب می‌شود.
            throw new ValidationException("id is null");
        }
        if (getCustomerById(id) == null) {
            //بررسی می‌کند که مشتری با شناسه id وجود داشته باشد. اگر وجود نداشته باشد، یک خطای ValidationException پرتاب می‌شود.
            throw new ValidationException("Customer is not found");
        }

        customerRepository.deleteCustomer(id);
    }
}
