//این خط مشخص می‌کند که این کلاس در پکیج ir.reyhaneh.hotelreservation.controllerAdvice قرار دارد.
package ir.reyhaneh.hotelreservation.controllerAdvice;
//این خطوط، کلاس‌ها و کتابخانه‌های مورد نیاز را ایمپورت می‌کنند
//یک annotation از کتابخانه‌ی Lombok که به صورت خودکار یک constructor با پارامترهای نهایی (final) ایجاد می‌کند.

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//مشخص می‌کند که این کلاس یک Aspect است و می‌تواند رفتارهای جانبی (Cross-cutting concerns) مانند مدیریت خطاها را به کد اضافه کند.
@Aspect
//این کلاس را به عنوان یک Bean در Spring context ثبت می‌کند.
@Component
//یک constructor با پارامترهای نهایی (final) ایجاد می‌کند.
@RequiredArgsConstructor
public class ErrorControllerAspect {
    //این خط یک Logger ایجاد می‌کند که برای ثبت خطاها و اطلاعات استفاده می‌شود.
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //این annotation مشخص می‌کند که ااین نقطه‌ی اتصال (Pointcut) مشخص می‌کند که این Advice برای تمام متدهای موجود در کلاس‌هایی که نام آن‌ها با Controller پایان می‌یابد (یعنی کنترلرها) اعمال می‌شود.ین متد به عنوان یک Advice عمل می‌کند و قبل و بعد از اجرای متدهای هدف (Target methods) اجرا می‌شود.
    //این نقطه‌ی اتصال (Pointcut) مشخص می‌کند که این Advice برای تمام متدهای موجود در کلاس‌هایی که نام آن‌ها با Controller پایان می‌یابد (یعنی کنترلرها) اعمال می‌شود.
    @Around("within(ir.reyhaneh.hotelreservation.controller.*Controller)")
    //این متد یک Advice از نوع @Around است که قبل و بعد از اجرای متدهای هدف (Target methods) اجرا می‌شود.
    //این پارامتر به متد اجازه می‌دهد تا اجرای متد هدف را ادامه دهد یا تغییراتی در آن اعمال کند.
    public Object handleExceptions(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
// این خط اجرای متد هدف (متد کنترلر) را ادامه می‌دهد. اگر متد هدف بدون خطا اجرا شود، نتیجه‌ی آن به کلاینت برگردانده می‌شود.
            //اگر خطایی رخ ندهد، این بخش اجرا شده و نتیجه‌ی متد هدف بازگردانده می‌شود.
            return joinPoint.proceed();
            //این بخش خطاهای اعتبارسنجی (Validation errors) را مدیریت می‌کند. این خطا زمانی رخ می‌دهد که داده‌های ارسالی از سمت کلاینت معتبر نباشند
        } catch (MethodArgumentNotValidException ex) {
            //این خط جزئیات خطای اعتبارسنجی را در لاگ ثبت می‌کند
            log.error("Validation error in {} - {}",
                    joinPoint.getSignature().getName(),
                    ex.getMessage()
            );
//ex.getBindingResult().getFieldErrors(): لیستی از خطاهای مربوط به فیلدها را برمی‌گرداند.
//
//.stream(): این لیست را به یک Stream تبدیل می‌کند تا بتوان عملیات‌های Stream را روی آن انجام داد.
//
//.collect(Collectors.toMap(...)): خطاها را به یک Map تبدیل می‌کند:
//
//FieldError::getField: نام فیلدی که خطا در آن رخ داده است.
//
//error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : "Invalid value": پیام خطا را برمی‌گرداند. اگر پیام خطا null باشد، به جای آن از "Invalid value" استفاده می‌شود.
            Map<String, String> errors = ex.getBindingResult()

                    .getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : "Invalid value"
                    ));
//این خط یک شیء از نوع Map ایجاد می‌کند که برای ذخیره‌ی اطلاعات پاسخ خطا استفاده می‌شود. کلیدهای این Map رشته‌ها (String) و مقادیر آن‌ها از نوع Object هستند تا بتوانند انواع مختلف داده‌ها (مانند رشته، عدد، یا لیست) را ذخیره کنند.
            Map<String, Object> response = new HashMap<>();
            //این خط یک کلید به نام status به Map اضافه می‌کند و مقدار آن را "ERROR" قرار می‌دهد. این نشان می‌دهد که درخواست با خطا مواجه شده است.
            response.put("status", "ERROR");
            //این خط یک کلید به نام code به Map اضافه می‌کند و مقدار آن را برابر با کد وضعیت HTTP 400 (Bad Request) قرار می‌دهد. این کد نشان می‌دهد که درخواست ارسالی از سمت کلاینت نامعتبر است.
            response.put("code", HttpStatus.BAD_REQUEST.value());
            //این خط یک کلید به نام message به Map اضافه می‌کند و مقدار آن را "Validation failed" قرار می‌دهد. این پیام توضیحی کلی درباره‌ی خطای رخ داده ارائه می‌دهد.
            response.put("message", "Validation failed");
            //این خط یک کلید به نام errors به Map اضافه می‌کند و مقدار آن را برابر با errors قرار می‌دهد. errors یک Map دیگر است که شامل جزئیات خطاهای اعتبارسنجی است.
            response.put("errors", errors);
//این خط یک شیء از نوع ResponseEntity ایجاد می‌کند که شامل:
//بدنه‌ی پاسخ (response): همان Mapی که ساخته‌ایم و شامل وضعیت، کد، پیام، و جزئیات خطاها است.
//کد وضعیت HTTP (HttpStatus.BAD_REQUEST): مقدار 400 که نشان‌دهنده‌ی یک درخواست نامعتبر است.
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//این بخش خطاهای مربوط به IllegalArgumentException را مدیریت می‌کند. این خطا معمولاً زمانی رخ می‌دهد که ورودی‌های نامعتبر به متد یا تابع ارسال می‌شوند.
        } catch (IllegalArgumentException ex) {
            //این خط پیام خطا را در لاگ‌ها ثبت می‌کند. اطلاعاتی مانند نام متد (joinPoint.getSignature().getName()) و پیام خطا (ex.getMessage()) در لاگ ذخیره می‌شوند.
            log.error("Invalid argument in {} - {}",
                    joinPoint.getSignature().getName(),
                    ex.getMessage()
            );
//یک شیء Map ایجاد می‌شود که حاوی اطلاعات خطا است
            Map<String, Object> response = new HashMap<>();
            //وضعیت خطا (ERROR).
            response.put("status", "ERROR");
            //کد وضعیت HTTP (در اینجا 400 Bad Request).
            response.put("code", HttpStatus.BAD_REQUEST.value());
            //پیام خطا که از ex.getMessage() گرفته می‌شود.
            response.put("message", ex.getMessage());
//یک پاسخ HTTP با کد وضعیت 400 Bad Request و بدنه‌ی حاوی اطلاعات خطا (response) به کلاینت بازگردانده می‌شود.
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//این بخش خطاهای عمومی و غیرمنتظره (Exception) را مدیریت می‌کند. این خطاها معمولاً خطاهایی هستند که پیش‌بینی نشده‌اند.
        } catch (Exception ex) {
            //این خط پیام خطا را در لاگ‌ها ثبت می‌کند. علاوه بر نام متد و پیام خطا، خود شیء خطا (ex) نیز برای جزئیات بیشتر در لاگ ذخیره می‌شود.
            log.error("Unexpected error in {} - {}",
                    joinPoint.getSignature().getName(),
                    ex.getMessage(),
                    ex
            );
//یک شیء Map ایجاد می‌شود که حاوی اطلاعات خطا است:
            Map<String, Object> response = new HashMap<>();
            //وضعیت خطا (ERROR).
            response.put("status", "ERROR");
            //کد وضعیت HTTP (در اینجا 500 Internal Server Error).
            response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            //یک پیام کلی خطا (An unexpected error occurred).
            response.put("message", "An unexpected error occurred");
//اگر حالت دیباگ (debug) فعال باشد، پیام خطا (ex.getMessage()) نیز به پاسخ اضافه می‌شود. این کار برای کمک به توسعه‌دهندگان در تشخیص مشکل است.
            if (log.isDebugEnabled()) {
                response.put("debug_message", ex.getMessage());
            }
//یک پاسخ HTTP با کد وضعیت 500 Internal Server Error و بدنه‌ی حاوی اطلاعات خطا (response) به کلاینت بازگردانده می‌شود.
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
