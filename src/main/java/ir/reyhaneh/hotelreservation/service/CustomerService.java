//این خط مشخص می‌کند که اینترفیس CustomerService در پکیج ir.reyhaneh.hotelreservation.service قرار دارد.
package ir.reyhaneh.hotelreservation.service;
//این خطوط کتابخانه‌ها و کلاس‌های مورد نیاز را ایمپورت می‌کنند
//کلاس مدل مربوط به مشتریان

import ir.reyhaneh.hotelreservation.model.Customers;

import java.util.List;

//این خط یک اینترفیس به نام CustomerService تعریف می‌کند. اینترفیس‌ها در جاوا برای تعریف قراردادهایی (Contract) استفاده می‌شوند که کلاس‌های دیگر باید آن‌ها را پیاده‌سازی کنند.
public interface CustomerService {
    //این متد یک لیست از تمام مشتریان (Customers) را برمی‌گرداند.
//کاربرد: برای دریافت تمام مشتریان موجود در سیستم.
    List<Customers> getAllCustomers();

    //این متد یک مشتری (Customers) را بر اساس شناسه (id) آن برمی‌گرداند.
//کاربرد: برای دریافت اطلاعات یک مشتری خاص با استفاده از شناسه آن.
    Customers getCustomerById(Long id);

    //این متد یک مشتری جدید را ذخیره می‌کند.
//
//پارامترها:
//
//name: نام مشتری.
//
//email: ایمیل مشتری.
//
//phone: شماره تلفن مشتری.
//
//address: آدرس مشتری.
//
//کاربرد: برای ایجاد و ذخیره یک مشتری جدید در سیستم.
    void saveCustomer(String name, String email, String phone, String address);

    //این متد اطلاعات یک مشتری موجود را به‌روزرسانی می‌کند.
//
//پارامترها:
//
//id: شناسه مشتری که باید به‌روزرسانی شود.
//
//name: نام جدید مشتری.
//
//email: ایمیل جدید مشتری.
//
//phone: شماره تلفن جدید مشتری.
//
//address: آدرس جدید مشتری.
//
//کاربرد: برای به‌روزرسانی اطلاعات یک مشتری موجود در سیستم.
    void updateCustomer(Long id, String name, String email, String phone, String address);

    //این متد یک مشتری را بر اساس شناسه (id) آن حذف می‌کند.
//کاربرد: برای حذف یک مشتری از سیستم.
    void deleteCustomer(Long id);

}
