package ir.reyhaneh.hotelreservation.repository;

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

@Repository
public class PaymentsRepository {

    private final RowMapper<Payments> paymentsRowMapper = new RowMapper<Payments>() {
        @Override
        public Payments mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Payments(
                    rs.getLong("id"),
                    rs.getLong("amount"),
                    rs.getDate("paymentDate"),
                    rs.getString("paymentMethod")
            );
        }
    };
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public Long addPayments(Long amount, Date paymentDate, String paymentMethod) {
        String sql = "INSERT INTO payments (amount, paymentDate,paymentMethod) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                amount,
                paymentDate,
                paymentMethod);

        String lastInsertIdQuery = "SELECT LAST_INSERT_ID()";
        return jdbcTemplate.queryForObject(lastInsertIdQuery, Long.class);
    }

    @Transactional
    public void editPayments(Long id, Long amount, Date paymentDate, String paymentMethod) {
        String sql = "UPDATE payments SET amount=?, paymentDate = ?,paymentMethod = ? WHERE id=?";
        jdbcTemplate.update(sql,
                amount,
                paymentDate,
                paymentMethod,
                id);
    }

    @Transactional
    public void deletePayments(Long id) {
        String sql = "DELETE from payments WHERE id=?";
        jdbcTemplate.update(sql,
                id);
    }

    @Transactional
    public Payments getPayments(Long id) {
        String sql = "SELECT * FROM payments WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, paymentsRowMapper, id);
    }

    public List<Payments> getAllPayments() {
        String sql = "SELECT * FROM payments";
        return jdbcTemplate.query(sql, paymentsRowMapper);
    }

}
