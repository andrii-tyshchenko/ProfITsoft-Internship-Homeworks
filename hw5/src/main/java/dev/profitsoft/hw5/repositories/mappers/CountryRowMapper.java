package dev.profitsoft.hw5.repositories.mappers;

import dev.profitsoft.hw5.entities.Country;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryRowMapper implements RowMapper<Country> {
    // method getObject() for Integer.class is used instead of getInteger()
    // because if for some reasons corresponding value is equal to null,
    // method getInteger() will return 0, and method getObject() - null
    @Override
    public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Country(
                rs.getObject("country_id", Integer.class),
                rs.getString("country_name"),
                rs.getString("country_iso_code")
        );
    }
}