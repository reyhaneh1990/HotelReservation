//این خط مشخص می‌کند که کلاس AdditionalServiceRepository در پکیج ir.reyhaneh.hotelreservation.repository قرار دارد.
package ir.reyhaneh.hotelreservation.repository;
//این خطوط کتابخانه‌ها و کلاس‌های مورد نیاز را ایمپورت می‌کنند
//کلاس مدل مربوط به سرویس‌های اضافی.

import ir.reyhaneh.hotelreservation.model.AdditionalService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//این annotation مشخص می‌کند که این کلاس یک ریپوزیتوری است و توسط اسپرینگ به عنوان یک bean مدیریت می‌شود. این کلاس مسئول تعامل با پایگاه داده است.
@Repository
//
public class AdditionalServiceRepository {
    //این بخش یک RowMapper تعریف می‌کند که ردیف‌های جدول additionalService را به اشیاء AdditionalService نگاشت می‌کند.
    private final RowMapper<AdditionalService> additionalServiceRowMapper = new RowMapper<AdditionalService>() {
        //mapRow: این متد هر ردیف از نتیجه‌ی کوئری SQL را به یک شیء AdditionalService تبدیل می‌کند.
        @Override
        public AdditionalService mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new AdditionalService(
                    //مقدار ستون id را از نتیجه‌ی کوئری می‌خواند.
                    rs.getLong("id"),
                    //مقدار ستون name را از نتیجه‌ی کوئری می‌خواند.
                    rs.getString("name"),
                    //مقدار ستون price را از نتیجه‌ی کوئری می‌خواند.
                    rs.getLong("price")
            );
        }
    };
    //این annotation برای تزریق خودکار JdbcTemplate توسط اسپرینگ استفاده می‌شود.
    @Autowired
    //این کلاس برای اجرای کوئری‌های SQL و مدیریت ارتباط با پایگاه داده استفاده می‌شود.
    private JdbcTemplate jdbcTemplate;

    //این annotation مشخص می‌کند که این متد باید در یک تراکنش اجرا شود.
//این متد یک سرویس اضافی جدید را به جدول additionalService اضافه می‌کند.
    @Transactional
    public void addAdditionalService(String name, Long price) {
        String sql = "INSERT INTO additional_service (name,price) VALUES (?, ?)";
        //این متد کوئری SQL را اجرا می‌کند و مقادیر name و price را به عنوان پارامترهای کوئری ارسال می‌کند.
        jdbcTemplate.update(sql,
                name,
                price);
    }

    //این annotation مشخص می‌کند که این متد باید در یک تراکنش اجرا شود.
//این متد اطلاعات یک سرویس اضافی موجود را در جدول additionalService به‌روزرسانی می‌کند.
    @Transactional
    public void editAdditionalService(String name, Long price) {
        String sql = "UPDATE additional_service SET name=?, price=? WHERE id=?";
        //این متد کوئری SQL را اجرا می‌کند و مقادیر name و price را به عنوان پارامترهای کوئری ارسال می‌کند.
        jdbcTemplate.update(sql,
                name,
                price);
    }

    //ین annotation مشخص می‌کند که این متد باید در یک تراکنش اجرا شود.
//این متد یک سرویس اضافی را از جدول additionalService حذف می‌کند.
    @Transactional
    public void deleteC(Long id) {
        String sql = "DELETE from additional_service WHERE id=?";
        //این متد کوئری SQL را اجرا می‌کند و مقدار id را به عنوان پارامتر کوئری ارسال می‌کند.
        jdbcTemplate.update(sql,
                id);
    }

    //این annotation مشخص می‌کند که این متد باید در یک تراکنش اجرا شود.
//این متد اطلاعات یک سرویس اضافی را بر اساس id از جدول additionalService بازیابی می‌کند.
    @Transactional
    public AdditionalService getAdditionalService(Long id) {
        String sql = "SELECT * FROM additional_service WHERE id = ?";
        //این متد کوئری SQL را اجرا می‌کند و نتیجه را به یک شیء AdditionalService نگاشت می‌کند.
        return jdbcTemplate.queryForObject(sql, additionalServiceRowMapper, id);
    }

    //این متد لیستی از تمام سرویس‌های اضافی را از جدول additionalService بازیابی می‌کند.
    public List<AdditionalService> getAllAdditionalService() {
        String sql = "SELECT * FROM additional_service";
        //این متد کوئری SQL را اجرا می‌کند و نتایج را به لیستی از اشیاء AdditionalService نگاشت می‌کند.
        return jdbcTemplate.query(sql, additionalServiceRowMapper);
    }

}
