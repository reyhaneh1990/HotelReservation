//این خط مشخص می‌کند که کلاس BookingRepository در پکیج ir.reyhaneh.hotelreservation.repository قرار دارد.
package ir.reyhaneh.hotelreservation.repository;
//این خطوط کتابخانه‌ها و کلاس‌های مورد نیاز را ایمپورت می‌کنند
//کلاس مدل مربوط به رزروها.

import ir.reyhaneh.hotelreservation.model.Bookings;
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
public class BookingRepository {
    //این بخش یک RowMapper تعریف می‌کند که ردیف‌های جدول booking را به اشیاء Bookings نگاشت می‌کند.
//mapRow: این متد هر ردیف از نتیجه‌ی کوئری SQL را به یک شیء Bookings تبدیل می‌کند.
    private final RowMapper<Bookings> bookingsRowMapper = new RowMapper<Bookings>() {
        @Override
        public Bookings mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Bookings(
                    //مقدار ستون id را از نتیجه‌ی کوئری می‌خواند.
                    rs.getLong("id"),
                    //مقدار ستون bookingDate را از نتیجه‌ی کوئری می‌خواند.
                    rs.getDate("bookingDate"),
                    //مقدار ستون checkInDate را از نتیجه‌ی کوئری می‌خواند.
                    rs.getDate("checkInDate"),
                    //مقدار ستون checkOutDate را از نتیجه‌ی کوئری می‌خواند.
                    rs.getDate("checkOutDate")
            );
        }
    };
    //این annotation برای تزریق خودکار JdbcTemplate توسط اسپرینگ استفاده می‌شود
    @Autowired
    //این کلاس برای اجرای کوئری‌های SQL و مدیریت ارتباط با پایگاه داده استفاده می‌شود.
    private JdbcTemplate jdbcTemplate;

    //این annotation مشخص می‌کند که این متد باید در یک تراکنش اجرا شود.
    @Transactional
    //این متد یک رزرو جدید را به جدول booking اضافه می‌کند.
    public void addBookings(Date bookingDate, Date checkInDate, Date checkOutDate, Long customerId, Long roomId, Long paymentId) {
        String sql = "INSERT INTO bookings (bookingDate,checkInDate,checkOutDate,customer_id,room_id,payment_id) VALUES (?, ?, ?, ?,?,?)";
        //این متد کوئری SQL را اجرا می‌کند و مقادیر bookingDate, checkInDate, checkOutDate, customerId, roomId و paymentId را به عنوان پارامترهای کوئری ارسال می‌کند.
        jdbcTemplate.update(sql,
                bookingDate,
                checkInDate,
                checkOutDate,
                customerId,
                roomId,
                paymentId);
    }

    //این annotation مشخص می‌کند که این متد باید در یک تراکنش اجرا شود.
    @Transactional
    //این متد اطلاعات یک رزرو موجود را در جدول booking به‌روزرسانی می‌کند.
    public void editBooking(Date bookingDate, Date checkInDate, Date checkOutDate) {
        String sql = "UPDATE bookings SET bookingDate=?, checkInDate=?, checkOutDate=? WHERE id=?";
        //این متد کوئری SQL را اجرا می‌کند و مقادیر bookingDate, checkInDate و checkOutDate را به عنوان پارامترهای کوئری ارسال می‌کند.
        jdbcTemplate.update(sql,
                bookingDate,
                checkInDate,
                checkOutDate);
    }

    //این annotation مشخص می‌کند که این متد باید در یک تراکنش اجرا شود.
    @Transactional
    //این متد یک رزرو را از جدول booking حذف می‌کند.
    public void deleteBooking(Long id) {
        String sql = "DELETE from bookings WHERE id=?";
        //این متد کوئری SQL را اجرا می‌کند و مقدار id را به عنوان پارامتر کوئری ارسال می‌کند.
        jdbcTemplate.update(sql,
                id);
    }

    //این annotation مشخص می‌کند که این متد باید در یک تراکنش اجرا شود.
    @Transactional
    //این متد اطلاعات یک رزرو را بر اساس id از جدول booking بازیابی می‌کند.
    public Bookings getBooking(Long id) {
        String sql = "SELECT * FROM bookings WHERE id = ?";
        //این متد کوئری SQL را اجرا می‌کند و نتیجه را به یک شیء Bookings نگاشت می‌کند.
        return jdbcTemplate.queryForObject(sql, bookingsRowMapper, id);
    }

    //این متد لیستی از تمام رزروها را از جدول booking بازیابی می‌کند.
//
//jdbcTemplate.query: این متد کوئری SQL را اجرا می‌کند و نتایج را به لیستی از اشیاء Bookings نگاشت می‌کند.
    public List<Bookings> getAllBookings() {
        String sql = "SELECT * FROM bookings";
        return jdbcTemplate.query(sql, bookingsRowMapper);
    }

}
