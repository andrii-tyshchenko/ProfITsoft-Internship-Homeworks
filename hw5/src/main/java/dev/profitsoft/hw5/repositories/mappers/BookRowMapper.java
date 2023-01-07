package dev.profitsoft.hw5.repositories.mappers;

import dev.profitsoft.hw5.entities.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

public class BookRowMapper implements RowMapper<Book> {
    // method getObject() for Integer.class is used instead of getInteger()
    // because if for some reasons corresponding value is equal to null,
    // method getInteger() will return 0, and method getObject() - null
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Book(
                rs.getObject("book_id", Integer.class),
                rs.getString("book_title"),
                rs.getString("book_author"),
                new PublisherRowMapper().mapRow(rs, rowNum),
                rs.getString("book_isbn"),
                Year.of(rs.getObject("book_publishing_year", Integer.class))
        );
    }
}