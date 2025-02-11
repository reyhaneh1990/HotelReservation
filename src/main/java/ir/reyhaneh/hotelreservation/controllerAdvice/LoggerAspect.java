//این خط مشخص می‌کند که کلاس LoggerAspect در پکیج ir.reyhaneh.hotelreservation.controllerAdvice قرار دارد.
package ir.reyhaneh.hotelreservation.controllerAdvice;
//این خطوط کتابخانه‌ها و کلاس‌های مورد نیاز را ایمپورت می‌کنند
//برای دسترسی به اطلاعات درخواست HTTP.

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

//این annotation مشخص می‌کند که این کلاس یک Aspect است و قابلیت‌های AOP را فراهم می‌کند.
@Aspect
//این annotation مشخص می‌کند که این کلاس یک کامپوننت اسپرینگ است و توسط اسپرینگ مدیریت می‌شود.
@Component
//این annotation از کتابخانه Lombok برای ایجاد خودکار یک کانستراکتور با پارامترهای نهایی استفاده می‌کند.
@RequiredArgsConstructor
public class LoggerAspect {
    //این خط یک شیء Logger ایجاد می‌کند که برای ثبت لاگ‌ها استفاده می‌شود. این لاگر از کتابخانه SLF4J استفاده می‌کند.
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //ن annotation مشخص می‌کند که این Advice باید قبل و بعد از اجرای متدهای کنترلر فراخوانی شود.
//
//execution(* ir.reyhaneh.hotelreservation.controller.*Controller.*(..)): این عبارت نقطه‌قطع (Pointcut) را تعریف می‌کند.
// این Advice برای تمام متدهای کلاس‌هایی که در پکیج ir.reyhaneh.hotelreservation.controller قرار دارند و نام آن‌ها با Controller پایان می‌یابد، اعمال می‌شود
    @Around("execution(* ir.reyhaneh.hotelreservation.controller.*Controller.*(..))")
    //این متد Advice را پیاده‌سازی می‌کند. ProceedingJoinPoint به ما امکان می‌دهد تا متد هدف (Target Method) را اجرا کنیم و اطلاعات مربوط به آن را دریافت کنیم.
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //زمان شروع اجرای متد را ثبت می‌کند
        long start = System.currentTimeMillis();
        //نام کلاس و متد هدف را دریافت می‌کنند.
        String className = joinPoint.getSignature().getDeclaringTypeName();
        //اطلاعات درخواست HTTP را دریافت می‌کند.
        String methodName = joinPoint.getSignature().getName();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//اطلاعات مربوط به شروع اجرای متد، پارامترهای ورودی و URL درخواست را در لاگ ثبت می‌کند.
        log.info("==> Start executing {}.{}() with parameters: {}",
                className,
                methodName,
                Arrays.toString(joinPoint.getArgs()));
        log.info("Request URL: {} {}", request.getMethod(), request.getRequestURL().toString());

        try {
            //این خط متد هدف (Target Method) را اجرا می‌کند و نتیجه آن را در result ذخیره می‌کند.
            Object result = joinPoint.proceed();
            //زمان اجرای متد را محاسبه می‌کند.
            long executionTime = System.currentTimeMillis() - start;
//اطلاعات مربوط به پایان اجرای متد، نتیجه و زمان اجرا را در لاگ ثبت می‌کند.
            log.info("<== Finished executing {}.{}() with result: {}",
                    className,
                    methodName,
                    result);
            log.info("Method execution time: {} ms", executionTime);
//نتیجه متد هدف را بازمی‌گرداند.
            return result;
            //گر خطایی در حین اجرای متد هدف رخ دهد، این بخش خطا را مدیریت می‌کند
        } catch (Exception e) {
            //اطلاعات خطا (مانند علت و پیام خطا) را در لاگ ثبت می‌کند
            log.error("Exception in {}.{}() with cause = {}, message = {}",
                    className,
                    methodName,
                    e.getCause() != null ? e.getCause() : "NULL",
                    e.getMessage());
            //خطا را دوباره پرتاب می‌کند تا توسط سایر بخش‌های برنامه مدیریت شود
            throw e;
        }
    }
}
