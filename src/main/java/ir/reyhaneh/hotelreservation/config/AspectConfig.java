//این خط مشخص می‌کند که کلاس AspectConfig در پکیج ir.reyhaneh.hotelreservation.config قرار دارد. پکیج‌ها در جاوا برای سازمان‌دهی کدها و جلوگیری از تداخل نام‌ها استفاده می‌شوند.
package ir.reyhaneh.hotelreservation.config;
//این خط کلاس Configuration را از کتابخانه‌ی Spring فراخوانی می‌کند. این کلاس به Spring می‌گوید که این کلاس (AspectConfig) یک کلاس پیکربندی (Configuration) است و ممکن است شامل تعریف‌های Bean یا تنظیمات دیگر باشد.

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//این annotation به Spring می‌گوید که این کلاس (AspectConfig) یک کلاس پیکربندی است. کلاس‌های پیکربندی در Spring معمولاً برای تعریف Beanها یا تنظیمات اضافی استفاده می‌شوند.
@Configuration
//این annotation به Spring می‌گوید که قابلیت‌های AspectJ را فعال کند. با این کار، Spring می‌تواند از کلاس‌های Aspect (که برای پیاده‌سازی AOP استفاده می‌شوند) پشتیبانی کند.
//AOP (Aspect-Oriented Programming) یک پارادایم برنامه‌نویسی است که به شما امکان می‌دهد رفتارهای مشترک (مانند لاگینگ، تراکنش‌ها، مدیریت خطاها و ...) را در یک مکان جداگانه (به نام Aspect) تعریف کنید و سپس آن‌ها را به صورت خودکار به بخش‌های مختلف برنامه تزریق کنید.
@EnableAspectJAutoProxy

public class AspectConfig {
}
