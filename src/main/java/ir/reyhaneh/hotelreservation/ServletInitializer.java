//این خط مشخص می‌کند که کلاس ServletInitializer در پکیج ir.reyhaneh.hotelreservation قرار دارد.
package ir.reyhaneh.hotelreservation;
// این خطوط کتابخانه‌ها و کلاس‌های مورد نیاز را ایمپورت می‌کنند
//برای پیکربندی و تنظیم برنامه ا

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//این کلاس از SpringBootServletInitializer ارث‌بری می‌کند. این کلاس پایه برای برنامه‌های اسپرینگ بوت است که قرار است در یک سرور خارجی (مانند Apache Tomcat) اجرا شوند.
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    //این متد برای پیکربندی برنامه اسپرینگ بوت هنگام اجرا در یک سرور خارجی استفاده می‌شود.
    //این پارامتر یک builder برای پیکربندی برنامه اسپرینگ بوت است.
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        //این خط مشخص می‌کند که کلاس اصلی برنامه (HotelReservationApplication) باید به عنوان نقطه شروع برنامه استفاده شود.
        //کاربرد: این متد برنامه اسپرینگ بوت را برای اجرا در یک سرور خارجی پیکربندی می‌کند.
        return application.sources(HotelReservationApplication.class);
    }

}
