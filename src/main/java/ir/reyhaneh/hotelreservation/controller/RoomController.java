//این خط مشخص می‌کند که این کلاس در پکیج ir.reyhaneh.hotelreservation.controller قرار دارد.
package ir.reyhaneh.hotelreservation.controller;
//این خطوط، کلاس‌ها و کتابخانه‌های مورد نیاز را ایمپورت می‌کنند

import ir.reyhaneh.hotelreservation.model.Rooms;
import ir.reyhaneh.hotelreservation.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//یک constructor با تمام پارامترها به صورت خودکار ایجاد می‌کند. در اینجا، RoomService به عنوان پارامتر به کنترلر تزریق می‌شود.
@AllArgsConstructor
//مشخص می‌کند که این کلاس یک کنترلر RESTful است و تمام خروجی‌ها به صورت JSON یا XML برگردانده می‌شوند.
@RestController
//پایه‌ی URL برای تمام endpointهای این کنترلر را تعیین می‌کند. در اینجا، تمام درخواست‌ها باید با /room/ شروع شوند.
@RequestMapping(path = "/room/")

public class RoomController {
    //این خط یک نمونه از RoomService را به عنوان یک فیلد نهایی (final) تعریف می‌کند. این فیلد از طریق constructor (که توسط @AllArgsConstructor ایجاد شده) مقداردهی می‌شود.
    private final RoomService roomService;

    //این annotation مشخص می‌کند که این متد به درخواست‌های GET پاسخ می‌دهد.
    @GetMapping("/")
    //لیستی از تمام اتاق‌ها را برمی‌گرداند.
    public List<Rooms> getAllRoom() {
        return roomService.getAllRooms();
    }

    //این متد به درخواست‌های GET با یک پارامتر id در URL پاسخ می‌دهد.
    @GetMapping("{id}")
    //مقدار id از URL استخراج شده و به عنوان پارامتر به متد ارسال می‌شود.
    public Rooms getRoomById(@PathVariable Long id) {
        //متدی از سرویس است که اطلاعات یک اتاق را بر اساس id دریافت می‌کند.
        return roomService.getRoomById(id);
    }

    //این متد به درخواست‌های POST پاسخ می‌دهد و پارامترهای type، status و price را از URL دریافت می‌کند.
    @PostMapping("{type}/{status}/{price}")
    //مقادیر type، status و price از URL استخراج می‌شوند.
    public void saveRoom(@PathVariable String type, @PathVariable String status, @PathVariable Long price) {
        //متدی از سرویس است که یک اتاق جدید را در دیتابیس ذخیره می‌کند.
        roomService.saveRoom(type, status, price);
    }

    //این متد به درخواست‌های PUT پاسخ می‌دهد و پارامترهای id، type، status و price را از URL دریافت می‌کند.
    @PutMapping("{id}/{type}/{status}/{price}/")
    //مقادیر id، type، status و price از URL استخراج می‌شوند.
    public void updateRoom(@PathVariable Long id, @PathVariable String type, @PathVariable String status, @PathVariable Long price) {
        //متدی از سرویس است که اطلاعات یک اتاق را بر اساس id به‌روزرسانی می‌کند.
        roomService.updateRoom(id, type, status, price);
    }

    //این متد به درخواست‌های DELETE پاسخ می‌دهد و پارامتر id را از URL دریافت می‌کند.
    @DeleteMapping("delete/{id}")
    //مقدار id از URL استخراج می‌شود.
    public void deleteRoom(@PathVariable Long id) {
        //متدی از سرویس است که یک اتاق را بر اساس id از دیتابیس حذف می‌کند.
        roomService.deleteRoom(id);
    }


}
