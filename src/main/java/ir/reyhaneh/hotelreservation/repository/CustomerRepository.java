package ir.reyhaneh.hotelreservation.repository;

import ir.reyhaneh.hotelreservation.model.Customers;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerRepository {

    private final RowMapper<Customers> customerRowMapper = new RowMapper<Customers>() {
        @Override
        public Customers mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Customers(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address")
            );
        }
    };
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void addCustomer(String name, String email, String phone, String address) {
        String sql = "INSERT INTO customer (name, email, phone, address) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                name,
                email,
                phone,
                address);
    }

    @Transactional
    public void editCustomer(Long id, String name, String email, String phone, String address) {
        String sql = "UPDATE customer SET name=?, email=?, phone=?, address=? WHERE id=?";
        jdbcTemplate.update(sql,
                name,
                email,
                phone,
                address,
                id);
    }

    @Transactional
    public void deleteCustomer(Long id) {
        String sql = "DELETE from customer WHERE id=?";
        jdbcTemplate.update(sql,
                id);
    }

    @Transactional
    public Customers getCustomer(Long id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, customerRowMapper, id);
    }

    public List<Customers> getAllCustomers() {
        String sql = "SELECT * FROM customer";
        return jdbcTemplate.query(sql, customerRowMapper);
    }

}
