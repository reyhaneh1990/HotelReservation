//این خط مشخص می‌کند که کلاس Payments در پکیج ir.reyhaneh.hotelreservation.model قرار دارد.
package ir.reyhaneh.hotelreservation.model;
//این خطوط کتابخانه‌ها و کلاس‌های مورد نیاز را ایمپورت می‌کنند
// استفاده از annotations مربوط به JPA (Java Persistence API) که برای ارتباط با پایگاه داده استفاده می‌شود.

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

//هدف: نگهداری اطلعات مربوط به پرداختها.
//این annotations از کتابخانه Lombok هستند و به صورت خودکار متدهای getter و setter برای فیلدهای کلاس ایجاد می‌کنند.
@Getter
@Setter
//این annotation مشخص می‌کند که این کلاس یک موجودیت (Entity) است و با یک جدول در پایگاه داده مرتبط است.
@Entity
//این annotation نام جدول مربوط به این موجودیت در پایگاه داده را مشخص می‌کند (در اینجا جدول payments).
@Table(name = "payments")
//این کلاس را قادر می‌سازد تا به صورت سریالایز شده ذخیره یا انتقال داده شود.
public class Payments implements Serializable {
    //این annotation مشخص می‌کند که فیلد id به عنوان کلید اصلی (Primary Key) جدول استفاده می‌شود.
    @Id
    //این annotation مشخص می‌کند که مقدار id به صورت خودکار توسط پایگاه داده تولید شود.
    @GeneratedValue(strategy = GenerationType.AUTO)
    //این فیلد برای ذخیره شناسه منحصر به فرد هر پرداخت استفاده می‌شود.
    private Long id;
    //این فیلد برای ذخیره مقدار پرداخت (مبلغ) استفاده می‌شود.
    private Long amount;
    //این فیلد برای ذخیره تاریخ پرداخت استفاده می‌شود.
    private Date paymentDate;
    //این فیلد برای ذخیره روش پرداخت (مانند کارت اعتباری، پرداخت آنلاین و غیره) استفاده می‌شود.
    private String paymentMethod;
    //این annotation نشان می‌دهد که یک رابطه یک به یک بین موجودیت Payments و موجودیت Bookings وجود دارد.
    //این مشخص می‌کند که داده‌های مربوط به Bookings فقط زمانی از پایگاه داده بارگذاری شوند که به آن‌ها دسترسی داشته باشیم (Lazy Loading).
    @OneToOne(fetch = FetchType.LAZY)
    //این فیلد برای ذخیره اطلاعات مربوط به رزروی که این پرداخت برای آن انجام شده است استفاده می‌شود.
    private Bookings bookings;

    //این یک کانستراکتور پارامتری است که برای ایجاد یک شیء Payments با مقادیر اولیه استفاده می‌شود.
    public Payments(Long id, Long amount, Date paymentDate, String paymentMethod) {
        this.id = id;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
    }

    //این یک کانستراکتور پیش‌فرض (بدون پارامتر) است که برای ایجاد یک شیء Payments بدون مقداردهی اولیه استفاده می‌شود.
    public Payments() {
    }
}