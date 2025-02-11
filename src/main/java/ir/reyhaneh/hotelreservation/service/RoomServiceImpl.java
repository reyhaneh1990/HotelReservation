//این خط مشخص می‌کند که کلاس RoomServiceImpl در پکیج ir.reyhaneh.hotelreservation.service قرار دارد.
package ir.reyhaneh.hotelreservation.service;
//این خطوط کتابخانه‌ها و کلاس‌های مورد نیاز را ایمپورت می‌کنند:
// کلاس مدل مربوط به اتاق‌ها.

import ir.reyhaneh.hotelreservation.model.Rooms;
import ir.reyhaneh.hotelreservation.repository.RoomRepository;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//این annotation مشخص می‌کند که این کلاس یک سرویس است و توسط اسپرینگ به عنوان یک bean مدیریت می‌شود.
@Service
//این annotation از کتابخانه Lombok برای ایجاد خودکار یک کانستراکتور با تمام پارامترها استفاده می‌کند.
@AllArgsConstructor
//این کلاس اینترفیس RoomService را پیاده‌سازی می‌کند و متدهای آن را تعریف می‌کند.
public class RoomServiceImpl implements RoomService {
    //این خط وابستگی مورد نیاز کلاس را تعریف می‌کند:
//roomRepository: ریپوزیتوری برای دسترسی به داده‌های اتاق‌ها.
    private final RoomRepository roomRepository;

    @Override
    //این متد لیستی از تمام اتاق‌ها را از ریپوزیتوری roomRepository دریافت می‌کند و برمی‌گرداند.
    //کاربرد: برای دریافت تمام اتاق‌های موجود در سیستم.
    public List<Rooms> getAllRooms() {
        return roomRepository.getAllRoom();
    }


    @Override
    //این متد یک اتاق را بر اساس شناسه (id) آن از ریپوزیتوری roomRepository دریافت می‌کند و برمی‌گرداند.
    //کاربرد: برای دریافت اطلاعات یک اتاق خاص با استفاده از شناسه آن.
    public Rooms getRoomById(Long id) {
        return roomRepository.getRoom(id);
    }

    @Override
    //این متد یک اتاق جدید را ذخیره می‌کند.
    public void saveRoom(String type, String status, Long price) {
        //اعتبارسنجی:
        //بررسی می‌کند که نوع اتاق (type) خالی نباشد. اگر خالی باشد، یک خطای ValidationException پرتاب می‌شود.
        //کاربرد: برای ایجاد و ذخیره یک اتاق جدید در سیستم.
        if (type.isEmpty()) {
            throw new ValidationException("Type is empty");
        }

        roomRepository.addRoom(type, status, price);
    }

    @Override
    //این متد اطلاعات یک اتاق موجود را به‌روزرسانی می‌کند
    public void updateRoom(Long id, String type, String status, Long price) {
        //بررسی می‌کند که شناسه (id) null نباشد. اگر null باشد، یک خطای ValidationException پرتاب می‌شود.
        if (id == null) {
            throw new ValidationException("id is null");
        }
        //بررسی می‌کند که اتاق با شناسه id وجود داشته باشد. اگر وجود نداشته باشد، یک خطای ValidationException پرتاب می‌شود.
        if (getRoomById(id) == null) {
            throw new ValidationException("Room is not found");
        }

        roomRepository.editRoom(id, type, status, price);
    }

    @Override
    //این متد یک اتاق را بر اساس شناسه (id) آن حذف می‌کند
    public void deleteRoom(Long id) {
        //بررسی می‌کند که شناسه (id) null نباشد. اگر null باشد، یک خطای ValidationException پرتاب می‌شود.
        if (id == null) {
            throw new ValidationException("id is null");
        }
        //بررسی می‌کند که اتاق با شناسه id وجود داشته باشد. اگر وجود نداشته باشد، یک خطای ValidationException پرتاب می‌شود.
        if (getRoomById(id) == null) {
            throw new ValidationException("Room is not found");
        }

        roomRepository.deleteRoom(id);
    }
}

