//این خط مشخص می‌کند که کلاس HotelReservationApplication در پکیج ir.reyhaneh.hotelreservation قرار دارد.
package ir.reyhaneh.hotelreservation;
//این خطوط کتابخانه‌ها و کلاس‌های مورد نیاز را ایمپورت می‌کنند
//برای راه‌اندازی برنامه اسپرینگ بوت.

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//این annotation ترکیبی از سه annotation دیگر است:
//
//@Configuration: مشخص می‌کند که این کلاس یک کلاس پیکربندی (Configuration) است.
//
//@EnableAutoConfiguration: پیکربندی خودکار برنامه اسپرینگ بوت را فعال می‌کند.
//
//@ComponentScan: به اسپرینگ دستور می‌دهد تا کامپوننت‌ها (مانند سرویس‌ها، کنترلرها و ریپوزیتوری‌ها) را در پکیج‌های زیرمجموعه اسکن کند.
//
//این کلاس به عنوان کلاس اصلی برنامه اسپرینگ بوت عمل می‌کند.
@SpringBootApplication
public class HotelReservationApplication {
    //این متد نقطه شروع برنامه است و زمانی که برنامه اجرا می‌شود، این متد فراخوانی می‌شود.
    //این متد برنامه اسپرینگ بوت را راه‌اندازی می‌کند. پارامترهای آن عبارتند از:
//
//HotelReservationApplication.class: کلاس اصلی برنامه که حاوی annotation @SpringBootApplication است.
//
//args: آرگومان‌های خط فرمان که به برنامه ارسال می‌شوند.
//
//کاربرد: این متد برنامه اسپرینگ بوت را راه‌اندازی می‌کند و تمام کامپوننت‌ها، سرویس‌ها و کنترلرها را بارگذاری و آماده استفاده می‌کند.
    public static void main(String[] args) {
        SpringApplication.run(HotelReservationApplication.class, args);
    }

}
