//این خط مشخص می‌کند که کلاس HomeController در پکیج ir.reyhaneh.hotelreservation.controller قرار دارد. پکیج‌ها در جاوا برای سازمان‌دهی کدها و جلوگیری از تداخل نام‌ها استفاده می‌شوند.
package ir.reyhaneh.hotelreservation.controller;
//این خطوط کلاس‌های لازم از کتابخانه‌ی Spring را فراخوانی می‌کنند. این کلاس‌ها برای تعریف یک کنترلر و مدیریت درخواست‌های HTTP استفاده می‌شوند:

//@GetMapping: برای تعریف یک متد که به درخواست‌های GET پاسخ می‌دهد.

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//این annotation به Spring می‌گوید که این کلاس (HomeController) یک کنترلر است و پاسخ‌ها را به صورت RESTful برمی‌گرداند. پاسخ‌ها معمولاً به صورت JSON یا متن ساده هستند.
//تفاوت @RestController با @Controller این است که در @RestController نیازی به استفاده از @ResponseBody نیست، زیرا تمام متدها به طور پیش‌فرض پاسخ‌ها را به صورت مستقیم به کلاینت برمی‌گردانند.
@RestController
//این annotation مسیر پایه‌ای (Base Path) را برای تمام متدهای این کنترلر تعریف می‌کند.
//در اینجا، مسیر پایه / است، یعنی تمام درخواست‌هایی که به این کنترلر ارسال می‌شوند، باید با / شروع شوند.
@RequestMapping(path = "/")
//این یک کلاس ساده است که نقش کنترلر را در برنامه ایفا می‌کند. این کلاس شامل متدهایی است که به درخواست‌های HTTP پاسخ می‌دهند.
public class HomeController {
    //این annotation مشخص می‌کند که متد greeting() به درخواست‌های GET که به مسیر / ارسال می‌شوند، پاسخ می‌دهد.
    //از آن‌جایی که مسیر پایه (@RequestMapping) نیز / است، این متد به درخواست‌های GET / پاسخ می‌دهد.
    @GetMapping("/")
    //این متد یک درخواست GET را مدیریت می‌کند و یک پاسخ متنی (String) برمی‌گرداند.
    //در اینجا، متن "Hello! I am Reyhaneh Jabbari 09911671428" به عنوان پاسخ برگردانده می‌شود.
    public String greeting() {
        return "Hello! I am Reyhaneh Jabbari 09911671428";
    }

}
