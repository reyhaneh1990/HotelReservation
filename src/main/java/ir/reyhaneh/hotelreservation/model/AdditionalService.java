//این خط مشخص می‌کند که این کلاس در پکیج ir.reyhaneh.hotelreservation.model قرار دارد.
package ir.reyhaneh.hotelreservation.model;
//این خطوط، کلاس‌ها و کتابخانه‌های مورد نیاز را ایمپورت می‌کنند
//کلاس‌ها و annotation‌های مربوط به JPA (Java Persistence API) برای نگاشت مدل به جدول دیتابیس.

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


// هدف: نگهداری اطلعات مربوط به خدمات اضافی که مشتریان میتوانند درخواست دهند.
//این anotation‌های Lombok به صورت خودکار متدهای getter و setter برای فیلدهای کلاس ایجاد می‌کنند.
@Getter
@Setter
//این annotation نشان می‌دهد که این کلاس یک Entity است و باید به یک جدول در دیتابیس نگاشت شود.
@Entity
//این annotation نام جدول دیتابیس را مشخص می‌کند. در اینجا، جدول additional_service برای ذخیره‌ی داده‌ها استفاده می‌شود.
@Table(name = "additional_service")
//این کلاس اینترفیس Serializable را پیاده‌سازی می‌کند تا بتوان آن را سریالایز کرد.
public class AdditionalService implements Serializable {
    //این annotation نشان می‌دهد که این فیلد کلید اصلی (Primary Key) جدول است.
    @Id
    //این annotation مشخص می‌کند که مقدار این فیلد به صورت خودکار توسط دیتابیس تولید شود.
    @GeneratedValue(strategy = GenerationType.AUTO)
    //این فیلد برای ذخیره‌ی شناسه‌ی منحصر به فرد هر سرویس اضافی استفاده می‌شود.
    private Long id;
    //این فیلد برای ذخیره‌ی نام سرویس اضافی استفاده می‌شود.
    private String name;
    //این فیلد برای ذخیره‌ی قیمت سرویس اضافی استفاده می‌شود.
    private Long price;
    //این annotation نشان می‌دهد که یک رابطه‌ی چندبهچند بین AdditionalService و Bookings وجود دارد. یعنی یک سرویس اضافی می‌تواند به چندین رزرو مرتبط باشد و یک رزرو می‌تواند چندین سرویس اضافی داشته باشد.
    @ManyToMany(fetch = FetchType.LAZY)
    //این فیلد لیستی از رزروها (Bookings) را نگهداری می‌کند که به این سرویس اضافی مرتبط هستند.
    private List<Bookings> bookings;

    //این یک سازنده (Constructor) است که برای ایجاد یک شیء AdditionalService با مقادیر مشخص استفاده می‌شود. این سازنده مقادیر id، name و price را دریافت کرده و آن‌ها را به فیلدهای مربوطه اختصاص می‌دهد.
    public AdditionalService(Long id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    //این یک سازنده‌ی پیش‌فرض (Default Constructor) است که توسط فریم‌ورک‌هایی مانند Hibernate یا Spring مورد نیاز است. این سازنده هیچ پارامتری نمی‌گیرد و فیلدها را مقداردهی نمی‌کند.
    public AdditionalService() {
    }
}
