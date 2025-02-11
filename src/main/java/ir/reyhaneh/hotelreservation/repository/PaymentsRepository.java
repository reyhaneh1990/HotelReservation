//این خط مشخص می‌کند که کلاس PaymentsRepository در پکیج ir.reyhaneh.hotelreservation.repository قرار دارد.
package ir.reyhaneh.hotelreservation.repository;
//این خطوط کتابخانه‌ها و کلاس‌های مورد نیاز را ایمپورت می‌کنند
//کلاس مدل مربوط به پرداخت‌ها

import ir.reyhaneh.hotelreservation.model.Payments;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

//این annotation مشخص می‌کند که این کلاس یک ریپوزیتوری است و توسط اسپرینگ به عنوان یک bean مدیریت می‌شود. این کلاس مسئول تعامل با پایگاه داده است.
@Repository
public class PaymentsRepository {
    //این بخش یک RowMapper تعریف می‌کند که ردیف‌های جدول payments را به اشیاء Payments نگاشت می‌کند.
    private final RowMapper<Payments> paymentsRowMapper = new RowMapper<Payments>() {
        @Override
        //این متد هر ردیف از نتیجه‌ی کوئری SQL را به یک شیء Payments تبدیل می‌کند.
        public Payments mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Payments(
                    //مقدار ستون id را از نتیجه‌ی کوئری می‌خواند.
                    rs.getLong("id"),
                    //مقدار ستون amount را از نتیجه‌ی کوئری می‌خواند.
                    rs.getLong("amount"),
                    //مقدار ستون paymentDate را از نتیجه‌ی کوئری می‌خواند.
                    rs.getDate("paymentDate"),
                    //مقدار ستون paymentMethod را از نتیجه‌ی کوئری می‌خواند.
                    rs.getString("paymentMethod")
            );
        }
    };
    //این annotation برای تزریق خودکار JdbcTemplate توسط اسپرینگ استفاده می‌شود.
    @Autowired
    //این کلاس برای اجرای کوئری‌های SQL و مدیریت ارتباط با پایگاه داده استفاده می‌شود.
    private JdbcTemplate jdbcTemplate;

    //این annotation مشخص می‌کند که این متد باید در یک تراکنش اجرا شود.
    @Transactional
    //این متد یک پرداخت جدید را به جدول payments اضافه می‌کند.
    public Long addPayments(Long amount, Date paymentDate, String paymentMethod) {
        String sql = "INSERT INTO payments (amount, paymentDate,paymentMethod) VALUES (?, ?, ?)";
        //این متد کوئری SQL را اجرا می‌کند و مقادیر amount, paymentDate و paymentMethod را به عنوان پارامترهای کوئری ارسال می‌کند.
        jdbcTemplate.update(sql,
                amount,
                paymentDate,
                paymentMethod);
//این کوئری آخرین شناسه (ID) تولید شده در جدول payments را برمی‌گرداند.
        String lastInsertIdQuery = "SELECT LAST_INSERT_ID()";
        //این متد کوئری SQL را اجرا می‌کند و نتیجه را به یک شیء Long نگاشت می‌کند (شناسه پرداخت جدید).
        return jdbcTemplate.queryForObject(lastInsertIdQuery, Long.class);
    }

    //این annotation مشخص می‌کند که این متد باید در یک تراکنش اجرا شود.
    @Transactional
    //این متد اطلاعات یک پرداخت موجود را در جدول payments به‌روزرسانی می‌کند.
    public void editPayments(Long id, Long amount, Date paymentDate, String paymentMethod) {
        String sql = "UPDATE payments SET amount=?, paymentDate = ?,paymentMethod = ? WHERE id=?";
        //این متد کوئری SQL را اجرا می‌کند و مقادیر amount, paymentDate, paymentMethod و id را به عنوان پارامترهای کوئری ارسال می‌کند.
        jdbcTemplate.update(sql,
                amount,
                paymentDate,
                paymentMethod,
                id);
    }

    //این annotation مشخص می‌کند که این متد باید در یک تراکنش اجرا شود.
    @Transactional
//این متد یک پرداخت را از جدول payments حذف می‌کند.
    public void deletePayments(Long id) {
        String sql = "DELETE from payments WHERE id=?";
        //این متد کوئری SQL را اجرا می‌کند و مقدار id را به عنوان پارامتر کوئری ارسال می‌کند.
        jdbcTemplate.update(sql,
                id);
    }

    //این annotation مشخص می‌کند که این متد باید در یک تراکنش اجرا شود.
    @Transactional
    //این متد اطلاعات یک پرداخت را بر اساس id از جدول payments بازیابی می‌کند.
    public Payments getPayments(Long id) {
        String sql = "SELECT * FROM payments WHERE id = ?";
        //این متد کوئری SQL را اجرا می‌کند و نتیجه را به یک شیء Payments نگاشت می‌کند.
        return jdbcTemplate.queryForObject(sql, paymentsRowMapper, id);
    }

    //این متد لیستی از تمام پرداخت‌ها را از جدول payments بازیابی می‌کند.
    public List<Payments> getAllPayments() {
        String sql = "SELECT * FROM payments";
        //این متد کوئری SQL را اجرا می‌کند و نتایج را به لیستی از اشیاء Payments نگاشت می‌کند.
        return jdbcTemplate.query(sql, paymentsRowMapper);
    }

}
