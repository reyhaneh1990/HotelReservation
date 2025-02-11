//این خط مشخص می‌کند که اینترفیس RoomService در پکیج ir.reyhaneh.hotelreservation.service قرار دارد.
package ir.reyhaneh.hotelreservation.service;
//این خطوط کتابخانه‌ها و کلاس‌های مورد نیاز را ایمپورت می‌کنند
// کلاس مدل مربوط به اتاق‌ها

import ir.reyhaneh.hotelreservation.model.Rooms;

import java.util.List;

//این خط یک اینترفیس به نام RoomService تعریف می‌کند. اینترفیس‌ها در جاوا برای تعریف قراردادهایی (Contract) استفاده می‌شوند که کلاس‌های دیگر باید آن‌ها را پیاده‌سازی کنند.
public interface RoomService {
    //این متد یک لیست از تمام اتاق‌ها (Rooms) را برمی‌گرداند.
//کاربرد: برای دریافت تمام اتاق‌های موجود در سیستم.
    List<Rooms> getAllRooms();

    // این متد یک اتاق (Rooms) را بر اساس شناسه (id) آن برمی‌گرداند.
    //کاربرد: برای دریافت اطلاعات یک اتاق خاص با استفاده از شناسه آن.
    Rooms getRoomById(Long id);
    //این متد یک اتاق جدید را ذخیره می‌کند.
    //
    //پارامترها:
    //
    //type: نوع اتاق (مانند سوئیت، اتاق دو تخته، اتاق یک تخته و غیره).
    //
    //status: وضعیت اتاق (مانند "خالی"، "رزرو شده"، "در حال تمیزکاری" و غیره).
    //
    //price: قیمت اتاق.
    //
    //کاربرد: برای ایجاد و ذخیره یک اتاق جدید در سیستم

    void saveRoom(String type, String status, Long price);

    //این متد اطلاعات یک اتاق موجود را به‌روزرسانی می‌کند.
//
//پارامترها:
//
//id: شناسه اتاق که باید به‌روزرسانی شود.
//
//type: نوع جدید اتاق.
//
//status: وضعیت جدید اتاق.
//
//price: قیمت جدید اتاق.
//
//کاربرد: برای به‌روزرسانی اطلاعات یک اتاق موجود در سیستم.
    void updateRoom(Long id, String type, String status, Long price);

    //این متد یک اتاق را بر اساس شناسه (id) آن حذف می‌کند.
//کاربرد: برای حذف یک اتاق از سیستم.
    void deleteRoom(Long id);

}





