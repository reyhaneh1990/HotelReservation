package ir.reyhaneh.hotelreservation.repository;

import ir.reyhaneh.hotelreservation.model.AdditionalService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AdditionalServiceRepository {

    private final RowMapper<AdditionalService> additionalServiceRowMapper = new RowMapper<AdditionalService>() {
        @Override
        public AdditionalService mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new AdditionalService(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getLong("price")
            );
        }
    };
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void addAdditionalService(String name, Long price) {
        String sql = "INSERT INTO additionalService (name,price) VALUES (?, ?)";
        jdbcTemplate.update(sql,
                name,
                price);
    }

    @Transactional
    public void editAdditionalService(String name, Long price) {
        String sql = "UPDATE additionalService SET name=?, price=? WHERE id=?";
        jdbcTemplate.update(sql,
                name,
                price);
    }

    @Transactional
    public void deleteC(Long id) {
        String sql = "DELETE from additionalService WHERE id=?";
        jdbcTemplate.update(sql,
                id);
    }

    @Transactional
    public AdditionalService getAdditionalService(Long id) {
        String sql = "SELECT * FROM additionalService WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, additionalServiceRowMapper, id);
    }

    public List<AdditionalService> getAllAdditionalService() {
        String sql = "SELECT * FROM additionalService";
        return jdbcTemplate.query(sql, additionalServiceRowMapper);
    }

}
