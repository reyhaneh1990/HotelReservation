package ir.reyhaneh.hotelreservation.controllerAdvice;

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

@Aspect
@Component
@RequiredArgsConstructor
public class LoggerAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* ir.reyhaneh.hotelreservation.controller.*Controller.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        log.info("==> Start executing {}.{}() with parameters: {}",
                className,
                methodName,
                Arrays.toString(joinPoint.getArgs()));
        log.info("Request URL: {} {}", request.getMethod(), request.getRequestURL().toString());

        try {
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - start;

            log.info("<== Finished executing {}.{}() with result: {}",
                    className,
                    methodName,
                    result);
            log.info("Method execution time: {} ms", executionTime);

            return result;
        } catch (Exception e) {
            log.error("Exception in {}.{}() with cause = {}, message = {}",
                    className,
                    methodName,
                    e.getCause() != null ? e.getCause() : "NULL",
                    e.getMessage());
            throw e;
        }
    }
}
