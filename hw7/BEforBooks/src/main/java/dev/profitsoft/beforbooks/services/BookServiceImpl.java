package dev.profitsoft.beforbooks.services;

import dev.profitsoft.beforbooks.dtos.BookDetailsDto;
import dev.profitsoft.beforbooks.dtos.BookSaveDto;
import dev.profitsoft.beforbooks.entities.Book;
import dev.profitsoft.beforbooks.entities.Publisher;
import dev.profitsoft.beforbooks.repositories.BookRepository;
import dev.profitsoft.beforbooks.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Year;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Integer createBook(BookSaveDto bookDto) {
        validateBookSaveDto(bookDto);

        Book book = convertToBook(bookDto);

        return bookRepository.save(book);
    }

    @Override
    public List<BookDetailsDto> getAllBooks() {
        List<Book> books = bookRepository.getAll();

        return books.stream()
                .map(BookServiceImpl::convertToBookDetailsDto)
                .toList();
    }

    /**
     * Returns a book with given id, converted to {@code BookDetailsDto} object.
     * Throws {@code ResponseStatusException} if it is not found.
     * @param id value of a book's id, which should be returned
     * @return book with given id, converted to {@code BookDetailsDto} object
     */
    @Override
    public BookDetailsDto getBookById(int id) {
        Book book = bookRepository.getById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Book with id=%d not found".formatted(id)
                        )
                );

        return convertToBookDetailsDto(book);
    }

    @Override
    public void updateBook(int id, BookSaveDto bookDto) {
        validateBookSaveDto(bookDto);

        Book book = convertToBook(bookDto);

        bookRepository.update(id, book);
    }

    /**
     * Deletes a book with given id or throws {@code ResponseStatusException} if deletion wasn't successful.
     * @param id value of a book's id, which should be deleted
     */
    @Override
    public void deleteBook(int id) {
        boolean unsuccessfulDeletion = !bookRepository.delete(id);

        if (unsuccessfulDeletion) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Couldn't delete: book with id=%d doesn't exist".formatted(id)
            );
        }
    }

    @Override
    public Boolean existsWithId(int id) {
        return bookRepository.existsWithId(id);
    }

    /**
     * Checks if a given {@code BookSaveDto} object has valid values for "publisherId" and "publishingYear" fields.
     * If some of them aren't valid - throws {@code IllegalArgumentException}.
     * @param bookDto object, which should be checked
     */
    private void validateBookSaveDto(BookSaveDto bookDto) {
        Integer publisherId = bookDto.getPublisherId();

        if (!publisherRepository.existsWithId(publisherId)) {
            throw new IllegalArgumentException("Publisher with id=%d doesn't exist".formatted(publisherId));
        }

        if (bookDto.getPublishingYear().isAfter(Year.now())) {
            throw new IllegalArgumentException("Publishing year should be before now");
        }
    }

    /**
     * Converts given {@code BookSaveDto} object to {@code Book} object.
     * @param dto object, which should be converted
     * @return {@code Book} object with fields values, taken from the given {@code BookSaveDto} object
     */
    private static Book convertToBook(BookSaveDto dto) {
        Book book = new Book();

        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPublisher(new Publisher(dto.getPublisherId(), null, null));
        book.setIsbn(dto.getIsbn());
        book.setPublishingYear(dto.getPublishingYear());

        return book;
    }

    /**
     * Converts given {@code Book} object to {@code BookDetailsDto} object.
     * @param book object, which should be converted
     * @return {@code BookDetailsDto} object with fields values, taken from the given {@code Book} object
     */
    private static BookDetailsDto convertToBookDetailsDto(Book book) {
        return new BookDetailsDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher().getId(),
                book.getPublisher().getName(),
                book.getIsbn(),
                book.getPublishingYear()
        );
    }
}