//این خط مشخص می‌کند که کلاس CustomerRepository در پکیج ir.reyhaneh.hotelreservation.repository قرار دارد.
package ir.reyhaneh.hotelreservation.repository;
//این خطوط کتابخانه‌ها و کلاس‌های مورد نیاز را ایمپورت می‌کنند
//کلاس مدل مربوط به مشتریان

import ir.reyhaneh.hotelreservation.model.Customers;
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
//این بخش یک RowMapper تعریف می‌کند که ردیف‌های جدول customer را به اشیاء Customers نگاشت می‌کند.
public class CustomerRepository {
    //این بخش یک RowMapper تعریف می‌کند که ردیف‌های جدول customer را به اشیاء Customers نگاشت می‌کند.
    private final RowMapper<Customers> customerRowMapper = new RowMapper<Customers>() {
        @Override
        //این متد هر ردیف از نتیجه‌ی کوئری SQL را به یک شیء Customers تبدیل می‌کند.
        public Customers mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Customers(
                    //مقدار ستون id را از نتیجه‌ی کوئری می‌خواند.
                    rs.getLong("id"),
                    //مقدار ستون name را از نتیجه‌ی کوئری می‌خواند.
                    rs.getString("name"),
                    //مقدار ستون email را از نتیجه‌ی کوئری می‌خواند.
                    rs.getString("email"),
                    //مقدار ستون phone را از نتیجه‌ی کوئری می‌خواند.
                    rs.getString("phone"),
                    //مقدار ستون address را از نتیجه‌ی کوئری می‌خواند.
                    rs.getString("address")
            );
        }
    };
    //این annotation برای تزریق خودکار JdbcTemplate توسط اسپرینگ استفاده می‌شود.
    @Autowired
    //این کلاس برای اجرای کوئری‌های SQL و مدیریت ارتباط با پایگاه داده استفاده می‌شود.
    private JdbcTemplate jdbcTemplate;

    //این annotation مشخص می‌کند که این متد باید در یک تراکنش اجرا شود.
    @Transactional
    //این متد یک مشتری جدید را به جدول customer اضافه می‌کند.
    public void addCustomer(String name, String email, String phone, String address) {
        String sql = "INSERT INTO customer (name, email, phone, address) VALUES (?, ?, ?, ?)";
        //این متد کوئری SQL را اجرا می‌کند و مقادیر name, email, phone و address را به عنوان پارامترهای کوئری ارسال می‌کند.
        jdbcTemplate.update(sql,
                name,
                email,
                phone,
                address);
    }

    //این annotation مشخص می‌کند که این متد باید در یک تراکنش اجرا شود.
    @Transactional
    //این متد اطلاعات یک مشتری موجود را در جدول customer به‌روزرسانی می‌کند
    public void editCustomer(Long id, String name, String email, String phone, String address) {
        String sql = "UPDATE customer SET name=?, email=?, phone=?, address=? WHERE id=?";
        //این متد کوئری SQL را اجرا می‌کند و مقادیر name, email, phone, address و id را به عنوان پارامترهای کوئری ارسال می‌کند.
        jdbcTemplate.update(sql,
                name,
                email,
                phone,
                address,
                id);
    }

    //این annotation مشخص می‌کند که این متد باید در یک تراکنش اجرا شود.
    @Transactional
//این متد یک مشتری را از جدول customer حذف می‌کند.
    public void deleteCustomer(Long id) {
        String sql = "DELETE from customer WHERE id=?";
        //این متد کوئری SQL را اجرا می‌کند و مقدار id را به عنوان پارامتر کوئری ارسال می‌کند.
        jdbcTemplate.update(sql,
                id);
    }

    //این annotation مشخص می‌کند که این متد باید در یک تراکنش اجرا شود.
    @Transactional
    //این متد اطلاعات یک مشتری را بر اساس id از جدول customer بازیابی می‌کند.
    public Customers getCustomer(Long id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        //این متد کوئری SQL را اجرا می‌کند و نتیجه را به یک شیء Customers نگاشت می‌کند.
        return jdbcTemplate.queryForObject(sql, customerRowMapper, id);
    }

    //این متد لیستی از تمام مشتریان را از جدول customer بازیابی می‌کند.
    public List<Customers> getAllCustomers() {
        String sql = "SELECT * FROM customer";
        //jdbcTemplate.query: این متد کوئری SQL را اجرا می‌کند و نتایج را به لیستی از اشیاء Customers نگاشت می‌کند.
        return jdbcTemplate.query(sql, customerRowMapper);
    }

}
