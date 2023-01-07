package dev.profitsoft.hw5.services;

import dev.profitsoft.hw5.dtos.BookDetailsDto;
import dev.profitsoft.hw5.dtos.BookInfoDto;
import dev.profitsoft.hw5.dtos.BookQueryDto;
import dev.profitsoft.hw5.dtos.BookSaveDto;

import java.util.List;

public interface BookService {
    /**
     * Creates a {@code Book} object from a given {@code BookSaveDto} object and returns its id.
     * @param bookDto object whose fields are used for creation of a {@code Book} object
     * @return id of the created {@code Book} object
     */
    Integer createBook(BookSaveDto bookDto);

    /**
     * Returns a book with given id, converted to {@code BookDetailsDto} object.
     * @param id value of a book's id, which should be returned
     * @return book with given id, converted to {@code BookDetailsDto} object
     */
    BookDetailsDto getBookById(int id);

    /**
     * Updates values for a book with a given id.
     * New values are represented in a given {@code BookSaveDto} object.
     * @param id value of a book's id, which should be updated
     * @param bookDto contains new values, which should be saved instead of the old ones
     */
    void updateBook(int id, BookSaveDto bookDto);

    /**
     * Deletes a book with given id.
     * @param id value of a book's id, which should be deleted
     */
    void deleteBook(int id);

    /**
     * Returns list of books, converted to {@code BookInfoDto} object, that match requirements of a given query.
     * @param query object, which fields' values are the parameters for the search query
     * @return list of books, which represented as {@code BookInfoDto} objects, matching query's requirements
     */
    List<BookInfoDto> searchBooks(BookQueryDto query);

    /**
     * Checks if a book with given id exists.
     * @param id value of a book's id, which should be checked
     * @return true if the book with given id exists
     */
    Boolean existsWithId(int id);
}