//پکیج: این کلاس در پکیج ir.reyhaneh.hotelreservation.config قرار دارد که معمولاً برای کلاس‌های مربوط به پیکربندی برنامه استفاده می‌شود.
package ir.reyhaneh.hotelreservation.config;
//ایمپورت‌ها: کلاس‌ها و اینترفیس‌های لازم برای پیکربندی Spring و اتصال به پایگاه‌داده وارد شده‌اند

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

// این annotation به Spring می‌گوید که این کلاس یک کلاس پیکربندی است و ممکن است شامل تعریف beanها باشد.
@Configuration
public class AppConfig {
    // این annotation به Spring می‌گوید که این متد یک bean تولید می‌کند که می‌تواند در دیگر بخش‌های برنامه تزریق شود.
    @Bean
    //این اینترفیس برای اتصال به پایگاه‌داده استفاده می‌شود. در اینجا از DriverManagerDataSource استفاده شده که یک پیاده‌سازی ساده از DataSource است.
    //
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //setDriverClassName: درایور JDBC برای پایگاه‌داده MySQL تنظیم شده است.
        //
        // URL پایگاه‌داده مشخص شده است.  پایگاه‌داده ای با MySQL روی localhost و پورت 3306 با نام hotel_reservation استفاده شده است.
        dataSource.setUrl("jdbc:mysql://localhost:3306/hotel_db");
        dataSource.setUsername("root");
        dataSource.setPassword("asd123!@#");
        return dataSource;
    }

    //این کلاس یک ابزار  در Spring است که عملیات JDBC را ساده‌تر می‌کند. با استفاده از JdbcTemplate می‌توان کوئری‌های SQL را اجرا کرد و نتایج را به راحتی مدیریت کرد.
//
//تزریق DataSource:JdbcTemplate به یک DataSource نیاز دارد که در اینجا از طریق پارامتر متد تزریق می‌شود.
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}