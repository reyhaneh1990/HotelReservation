package ir.reyhaneh.hotelreservation.repository;

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

@Repository
public class BookingRepository {

    private final RowMapper<Bookings> bookingsRowMapper = new RowMapper<Bookings>() {
        @Override
        public Bookings mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Bookings(
                    rs.getLong("id"),
                    rs.getDate("bookingDate"),
                    rs.getDate("checkInDate"),
                    rs.getDate("checkOutDate")
            );
        }
    };
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void addBookings(Date bookingDate, Date checkInDate, Date checkOutDate, Long customerId, Long roomId, Long paymentId) {
        String sql = "INSERT INTO booking (bookingDate,checkInDate,checkOutDate,customer_id,room_id,payment_id) VALUES (?, ?, ?, ?,?,?)";
        jdbcTemplate.update(sql,
                bookingDate,
                checkInDate,
                checkOutDate,
                customerId,
                roomId,
                paymentId);
    }

    @Transactional
    public void editBooking(Date bookingDate, Date checkInDate, Date checkOutDate) {
        String sql = "UPDATE booking SET bookingDate=?, checkInDate=?, checkOutDate=? WHERE id=?";
        jdbcTemplate.update(sql,
                bookingDate,
                checkInDate,
                checkOutDate);
    }

    @Transactional
    public void deleteBooking(Long id) {
        String sql = "DELETE from booking WHERE id=?";
        jdbcTemplate.update(sql,
                id);
    }

    @Transactional
    public Bookings getBooking(Long id) {
        String sql = "SELECT * FROM booking WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, bookingsRowMapper, id);
    }

    public List<Bookings> getAllBookings() {
        String sql = "SELECT * FROM booking";
        return jdbcTemplate.query(sql, bookingsRowMapper);
    }

}
