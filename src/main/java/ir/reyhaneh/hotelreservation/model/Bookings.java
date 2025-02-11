//این خط مشخص می‌کند که این کلاس در پکیج ir.reyhaneh.hotelreservation.model قرار دارد.
package ir.reyhaneh.hotelreservation.model;
//کلاس‌ها و annotation‌های مربوط به JPA (Java Persistence API) برای نگاشت مدل به جدول دیتابیس.

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

//هدف: مدیریت اطلعات رزروهای انجام شده توسط مشتریان.
//این annotation‌های Lombok به صورت خودکار متدهای getter و setter برای فیلدهای کلاس ایجاد می‌کنند.
@Getter
@Setter
//این annotation نشان می‌دهد که این کلاس یک Entity است و باید به یک جدول در دیتابیس نگاشت شود.
@Entity
//این annotation نام جدول دیتابیس را مشخص می‌کند. در اینجا، جدول bookings برای ذخیره‌ی داده‌ها استفاده می‌شود.
@Table(name = "bookings")
//این کلاس اینترفیس Serializable را پیاده‌سازی می‌کند تا بتوان آن را سریالایز کرد.
public class Bookings implements Serializable {
    //این annotation نشان می‌دهد که این فیلد کلید اصلی (Primary Key) جدول است.
    @Id
    //این annotation مشخص می‌کند که مقدار این فیلد به صورت خودکار توسط دیتابیس تولید شود.
    @GeneratedValue(strategy = GenerationType.AUTO)
    //این فیلد برای ذخیره‌ی شناسه‌ی منحصر به فرد هر رزرو استفاده می‌شود.
    private Long id;
    //این فیلد برای ذخیره‌ی تاریخ رزرو (تاریخی که مشتری رزرو را انجام داده است) استفاده می‌شود.
    private Date bookingDate;
    //این فیلد برای ذخیره‌ی تاریخ ورود (Check-in) مشتری به هتل استفاده می‌شود.
    private Date checkInDate;
    //این فیلد برای ذخیره‌ی تاریخ خروج (Check-out) مشتری از هتل استفاده می‌شود.
    private Date checkOutDate;
    //این annotation نشان می‌دهد که یک رابطه‌ی یک‌به‌یک بین Bookings و Payments وجود دارد. یعنی هر رزرو یک پرداخت مرتبط دارد.
    //fetch = FetchType.LAZY: این پارامتر مشخص می‌کند که داده‌های مرتبط (Payments) فقط زمانی از دیتابیس بارگذاری شوند که به آن‌ها دسترسی داشته باشیم (Lazy Loading).
    @OneToOne(fetch = FetchType.LAZY)
    //این annotation مشخص می‌کند که فیلد payment_id در جدول bookings به عنوان کلید خارجی (Foreign Key) برای ارتباط با جدول payments استفاده می‌شود.
    @JoinColumn(name = "payment_id")
    //این فیلد اطلاعات پرداخت مرتبط با این رزرو را نگهداری می‌کند.
    private Payments payments;
    //این annotation نشان می‌دهد که یک رابطه‌ی چندبه‌چند بین Bookings و AdditionalService وجود دارد. یعنی یک رزرو می‌تواند چندین سرویس اضافی داشته باشد و یک سرویس اضافی می‌تواند به چندین رزرو مرتبط باشد.
    //این پارامتر مشخص می‌کند که داده‌های مرتبط (AdditionalService) فقط زمانی از دیتابیس بارگذاری شوند که به آن‌ها دسترسی داشته باشیم (Lazy Loading).
    @ManyToMany(fetch = FetchType.LAZY)
    //این annotation مشخص می‌کند که یک جدول واسط به نام Booking_AdditionalService برای مدیریت رابطه‌ی چندبه‌چند ایجاد می‌شود.
    @JoinTable(name = "Booking_AdditionalService")
    //این فیلد لیستی از سرویس‌های اضافی مرتبط با این رزرو را نگهداری می‌کند.
    private List<AdditionalService> additionalServices;
    //این annotation نشان می‌دهد که یک رابطه‌ی چندبه‌یک بین Bookings و Customers وجود دارد. یعنی هر رزرو متعلق به یک مشتری است، اما یک مشتری می‌تواند چندین رزرو داشته باشد.
    //این پارامتر مشخص می‌کند که داده‌های مرتبط (Customers) فقط زمانی از دیتابیس بارگذاری شوند که به آن‌ها دسترسی داشته باشیم (Lazy Loading).
    @ManyToOne(fetch = FetchType.LAZY)
    //این annotation مشخص می‌کند که فیلد customer_id در جدول bookings به عنوان کلید خارجی (Foreign Key) برای ارتباط با جدول customers استفاده می‌شود.
    @JoinColumn(name = "customer_id")
    //این فیلد اطلاعات مشتری مرتبط با این رزرو را نگهداری می‌کند.
    private Customers customers;
    //این annotation نشان می‌دهد که یک رابطه‌ی چندبه‌یک بین Bookings و Rooms وجود دارد. یعنی هر رزرو متعلق به یک اتاق است، اما یک اتاق می‌تواند چندین رزرو داشته باشد.
    //: این پارامتر مشخص می‌کند که داده‌های مرتبط (Rooms) فقط زمانی از دیتابیس بارگذاری شوند که به آن‌ها دسترسی داشته باشیم
    @ManyToOne(fetch = FetchType.LAZY)
    //این annotation مشخص می‌کند که فیلد room_id در جدول bookings به عنوان کلید خارجی (Foreign Key) برای ارتباط با جدول rooms استفاده می‌شود.
    @JoinColumn(name = "room_id")
    //این فیلد اطلاعات اتاق مرتبط با این رزرو را نگهداری می‌کند.
    private Rooms rooms;

    //این یک سازنده (Constructor) است که برای ایجاد یک شیء Bookings با مقادیر مشخص استفاده می‌شود. این سازنده مقادیر id، bookingDate، checkInDate و checkOutDate را دریافت کرده و آن‌ها را به فیلدهای مربوطه اختصاص می‌دهد.
    public Bookings(Long id, Date bookingDate, Date checkInDate, Date checkOutDate) {
        this.id = id;
        this.bookingDate = bookingDate;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    //این یک سازنده‌ی پیش‌فرض (Default Constructor) است که توسط فریم‌ورک‌هایی مانند Hibernate یا Spring مورد نیاز است. این سازنده هیچ پارامتری نمی‌گیرد و فیلدها را مقداردهی نمی‌کند.
    public Bookings() {
    }
}
