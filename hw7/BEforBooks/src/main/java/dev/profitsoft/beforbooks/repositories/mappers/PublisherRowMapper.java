package dev.profitsoft.beforbooks.repositories.mappers;

import dev.profitsoft.beforbooks.entities.Publisher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PublisherRowMapper implements RowMapper<Publisher> {
    // method getObject() for Integer.class is used instead of getInteger()
    // because if for some reasons corresponding value is equal to null,
    // method getInteger() will return 0, and method getObject() - null
    @Override
    public Publisher mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Publisher(
                rs.getObject("publisher_id", Integer.class),
                rs.getString("publisher_name"),
                new CountryRowMapper().mapRow(rs, rowNum)
        );
    }
}