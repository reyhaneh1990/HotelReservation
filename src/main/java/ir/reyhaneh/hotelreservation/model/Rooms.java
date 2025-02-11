//این خط مشخص می‌کند که کلاس Rooms در پکیج ir.reyhaneh.hotelreservation.model قرار دارد.
package ir.reyhaneh.hotelreservation.model;
//این خطوط کتابخانه‌ها و کلاس‌های مورد نیاز را ایمپورت می‌کنند
//برای استفاده از annotations مربوط به JPA (Java Persistence API) که برای ارتباط با پایگاه داده استفاده می‌شود.

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

//هدف: نگهداری اطلعات مربوط به اتاقهای هتل.
//این annotations از کتابخانه Lombok هستند و به صورت خودکار متدهای getter و setter برای فیلدهای کلاس ایجاد می‌کنند.
@Getter
@Setter
//این annotation مشخص می‌کند که این کلاس یک موجودیت (Entity) است و با یک جدول در پایگاه داده مرتبط است.
@Entity
//این annotation نام جدول مربوط به این موجودیت در پایگاه داده را مشخص می‌کند (در اینجا جدول rooms).
@Table(name = "rooms")
//این کلاس را قادر می‌سازد تا به صورت سریالایز شده ذخیره یا انتقال داده شود.
public class Rooms implements Serializable {
    //این annotation مشخص می‌کند که فیلد id به عنوان کلید اصلی (Primary Key) جدول استفاده می‌شود.
    @Id
    //این annotation مشخص می‌کند که مقدار id به صورت خودکار توسط پایگاه داده تولید شود.
    @GeneratedValue(strategy = GenerationType.AUTO)
    //این annotation نام ستون مربوط به این فیلد در جدول پایگاه داده را مشخص می‌کند (در اینجا ستون id).
    @Column(name = "id")
    //این فیلد برای ذخیره شناسه منحصر به فرد هر اتاق استفاده می‌شود
    private Long id;
    //این فیلد برای ذخیره نوع اتاق (مانند سوئیت، اتاق دو تخته، اتاق یک تخته و غیره) استفاده می‌شود.
    private String type;
    //این فیلد برای ذخیره وضعیت اتاق (مانند "خالی"، "رزرو شده"، "در حال تمیزکاری" و غیره) استفاده می‌شود.
    private String status;
    //این فیلد برای ذخیره قیمت اتاق استفاده می‌شود.
    private Long price;
    //این annotation نشان می‌دهد که یک رابطه یک به چند بین موجودیت Rooms و موجودیت Bookings وجود دارد (یک اتاق می‌تواند چندین رزرو داشته باشد).
    //این مشخص می‌کند که داده‌های مربوط به Bookings فقط زمانی از پایگاه داده بارگذاری شوند که به آن‌ها دسترسی داشته باشیم (Lazy Loading).
    @OneToMany(fetch = FetchType.LAZY)
    //این فیلد برای ذخیره لیستی از رزروهای مربوط به این اتاق استفاده می‌شود.
    private List<Bookings> bookings;

    //این یک کانستراکتور پیش‌فرض (بدون پارامتر) است که برای ایجاد یک شیء Rooms بدون مقداردهی اولیه استفاده می‌شود.
    public Rooms() {
    }

    //این یک کانستراکتور پارامتری است که برای ایجاد یک شیء Rooms با مقادیر اولیه استفاده می‌شود.
    public Rooms(Long id, String type, String status, Long price) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.price = price;
    }
}
