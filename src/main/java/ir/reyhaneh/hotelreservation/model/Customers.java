//این خط مشخص می‌کند که این کلاس در پکیج ir.reyhaneh.hotelreservation.model قرار دارد.
package ir.reyhaneh.hotelreservation.model;
//این خطوط، کلاس‌ها و کتابخانه‌های مورد نیاز را ایمپورت می‌کنند
//کلاس‌ها و annotation‌های مربوط به JPA (Java Persistence API) برای نگاشت مدل به جدول دیتابیس.

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

//هدف: ذخیره اطلعات مربوط به مشتریانی که از هتل استفاده میکنند.
//این annotation‌های Lombok به صورت خودکار متدهای setter و getter برای فیلدهای کلاس ایجاد می‌کنند.
@Setter
@Getter
//این annotation نشان می‌دهد که این کلاس یک Entity است و باید به یک جدول در دیتابیس نگاشت شود
@Entity
//این annotation نام جدول دیتابیس را مشخص می‌کند. در اینجا، جدول customer برای ذخیره‌ی داده‌ها استفاده می‌شود.
@Table(name = "customer")
//این کلاس اینترفیس Serializable را پیاده‌سازی می‌کند تا بتوان آن را سریالایز کرد.
public class Customers implements Serializable {
    //این annotation نشان می‌دهد که این فیلد کلید اصلی (Primary Key) جدول است.
    @Id
    //این annotation مشخص می‌کند که مقدار این فیلد به صورت خودکار توسط دیتابیس تولید شود.
    @GeneratedValue(strategy = GenerationType.AUTO)
    //این فیلد برای ذخیره‌ی شناسه‌ی منحصر به فرد هر مشتری استفاده می‌شود.
    private Long id;
    //این فیلد برای ذخیره‌ی نام مشتری استفاده می‌شود.
    private String name;
    //این فیلد برای ذخیره‌ی آدرس ایمیل مشتری استفاده می‌شود.
    private String email;
    //این فیلد برای ذخیره‌ی شماره تلفن مشتری استفاده می‌شود.
    private String phone;
    //این فیلد برای ذخیره‌ی آدرس مشتری استفاده می‌شود.
    private String address;
    //این annotation نشان می‌دهد که یک رابطه‌ی یک‌به‌چند بین Customers و Bookings وجود دارد. یعنی یک مشتری می‌تواند چندین رزرو داشته باشد.
    //این پارامتر مشخص می‌کند که داده‌های مرتبط (Bookings) فقط زمانی از دیتابیس بارگذاری شوند که به آن‌ها دسترسی داشته باشیم (Lazy Loading).
    @OneToMany(fetch = FetchType.LAZY)
    //این فیلد لیستی از رزروهای مرتبط با این مشتری را نگهداری می‌کند.
    private List<Bookings> bookings;

    //این یک سازنده (Constructor) است که برای ایجاد یک شیء Customers با مقادیر مشخص استفاده می‌شود. این سازنده مقادیر id، name، email، phone و address را دریافت کرده و آن‌ها را به فیلدهای مربوطه اختصاص می‌دهد.
    public Customers(Long id, String name, String email, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    //این یک سازنده‌ی پیش‌فرض (Default Constructor) است که توسط فریم‌ورک‌هایی مانند Hibernate یا Spring مورد نیاز است. این سازنده هیچ پارامتری نمی‌گیرد و فیلدها را مقداردهی نمی‌کند.
    public Customers() {
    }
}
