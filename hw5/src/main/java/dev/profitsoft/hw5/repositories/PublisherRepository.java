package dev.profitsoft.hw5.repositories;

import dev.profitsoft.hw5.entities.Publisher;
import dev.profitsoft.hw5.repositories.mappers.PublisherRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Repository for operations with Publisher entities.
 */
@Repository
public class PublisherRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public PublisherRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Returns all publishers.
     * @return list of all publishers in database
     */
    public List<Publisher> getAll() {
        String sqlQuery = """
                SELECT p.id AS publisher_id, p.name AS publisher_name, 
                       c.id AS country_id, c.name AS country_name, iso_code AS country_iso_code
                FROM publishers p
                     LEFT JOIN countries c ON p.country_id = c.id
                """;

        return jdbcTemplate.query(sqlQuery, new PublisherRowMapper());
    }

    /**
     * Checks if a publisher with given id exists in a database.
     * @param id value of a publisher's id, which should be checked
     * @return true if the publisher with given id exists in the database
     */
    public Boolean existsWithId(int id) {
        String sqlQuery = """
                SELECT COUNT(id) > 0
                FROM publishers
                WHERE id = :id;
                """;

        Map<String, Integer> params = Map.of("id", id);

        return namedParameterJdbcTemplate.queryForObject(sqlQuery, params, Boolean.class);
    }
}