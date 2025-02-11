//این خط مشخص می‌کند که کلاس RoomRepository در پکیج ir.reyhaneh.hotelreservation.repository قرار دارد.
package ir.reyhaneh.hotelreservation.repository;
//این خطوط کتابخانه‌ها و کلاس‌های مورد نیاز را ایمپورت می‌کنند
//کلاس مدل مربوط به اتاق‌ها.

import ir.reyhaneh.hotelreservation.model.Rooms;
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
public class RoomRepository {
    //این بخش یک RowMapper تعریف می‌کند که ردیف‌های جدول room را به اشیاء Rooms نگاشت می‌کند.
    private final RowMapper<Rooms> RoomRowMapper = new RowMapper<Rooms>() {
        @Override
        //این متد هر ردیف از نتیجه‌ی کوئری SQL را به یک شیء Rooms تبدیل می‌کند.
        public Rooms mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Rooms(
                    //مقدار ستون id را از نتیجه‌ی کوئری می‌خواند.
                    rs.getLong("id"),
                    //مقدار ستون type را از نتیجه‌ی کوئری می‌خواند
                    rs.getString("type"),
                    //مقدار ستون status را از نتیجه‌ی کوئری می‌خواند.
                    rs.getString("status"),
                    //مقدار ستون price را از نتیجه‌ی کوئری می‌خواند.
                    rs.getLong("price")
            );
        }
    };
    //این annotation برای تزریق خودکار JdbcTemplate توسط اسپرینگ استفاده می‌شود.
    @Autowired
    //این کلاس برای اجرای کوئری‌های SQL و مدیریت ارتباط با پایگاه داده استفاده می‌شود.
    private JdbcTemplate jdbcTemplate;

    // این annotation مشخص می‌کند که این متد باید در یک تراکنش اجرا شود.
    @Transactional
    //این متد یک اتاق جدید را به جدول room اضافه می‌کند.
    public void addRoom(String type, String status, Long price) {
        String sql = "INSERT INTO room (type, status, price) VALUES (?, ?, ?)";
        //این متد کوئری SQL را اجرا می‌کند و مقادیر type, status و price را به عنوان پارامترهای کوئری ارسال می‌کند.
        jdbcTemplate.update(sql,
                type,
                status,
                price);
    }

    //این annotation مشخص می‌کند که این متد باید در یک تراکنش اجرا شود.
    @Transactional
    //این متد اطلاعات یک اتاق موجود را در جدول room به‌روزرسانی می‌کند.
    public void editRoom(Long id, String type, String status, Long price) {
        String sql = "UPDATE room SET type = ?, status = ?, price = ? WHERE id=?";
        //این متد کوئری SQL را اجرا می‌کند و مقادیر type, status, price و id را به عنوان پارامترهای کوئری ارسال می‌کند.
        jdbcTemplate.update(sql,
                type,
                status,
                price,
                id);
    }

    //این annotation مشخص می‌کند که این متد باید در یک تراکنش اجرا شود.
    @Transactional
    //این متد یک اتاق را از جدول room حذف می‌کند.
    public void deleteRoom(Long id) {
        String sql = "DELETE from room WHERE id=?";
        //این متد کوئری SQL را اجرا می‌کند و مقدار id را به عنوان پارامتر کوئری ارسال می‌کند.
        jdbcTemplate.update(sql,
                id);
    }

    //این annotation مشخص می‌کند که این متد باید در یک تراکنش اجرا شود.
    @Transactional
    //این متد اطلاعات یک اتاق را بر اساس id از جدول room بازیابی می‌کند.
    public Rooms getRoom(Long id) {
        String sql = "SELECT * FROM Room WHERE id = ?";
        //این متد کوئری SQL را اجرا می‌کند و نتیجه را به یک شیء Rooms نگاشت می‌کند.
        return jdbcTemplate.queryForObject(sql, RoomRowMapper, id);
    }

    //این متد لیستی از تمام اتاق‌ها را از جدول room بازیابی می‌کند
    public List<Rooms> getAllRoom() {
        String sql = "SELECT * FROM Room";
        //این متد کوئری SQL را اجرا می‌کند و نتایج را به لیستی از اشیاء Rooms نگاشت می‌کند.
        return jdbcTemplate.query(sql, RoomRowMapper);
    }

}
