package dev.profitsoft.hw5.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.profitsoft.hw5.dtos.BookDetailsDto;
import dev.profitsoft.hw5.dtos.BookInfoDto;
import dev.profitsoft.hw5.dtos.ErrorDetails;
import dev.profitsoft.hw5.entities.Book;
import dev.profitsoft.hw5.entities.Publisher;
import dev.profitsoft.hw5.repositories.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.Year;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private BookRepository bookRepository;

    private String title = "1984";
    private String author = "George Orwell";
    private int publisherId = 2;
    private String isbn = "9786177585359";
    private Integer publishingYear = 2021;

    @AfterEach
    void afterEach() {
        bookRepository.deleteAll();
    }

    @Test
    @DisplayName("Create valid book")
    void shouldCreateBook() throws Exception {
        String body = """
                {
                    "title": "%s",
                    "author": "%s",
                    "publisherId": %d,
                    "isbn": "%s",
                    "publishingYear": %d
                }
                """.formatted(title, author, publisherId, isbn, publishingYear);

        MvcResult mvcResult = mvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andReturn();

        int expectedId = 1;
        Integer actualId = Integer.parseInt(mvcResult.getResponse().getContentAsString());

        assertNotNull(actualId);
        assertThat(actualId).isGreaterThanOrEqualTo(expectedId);

        Book book = bookRepository.getById(actualId).orElse(null);

        assertNotNull(book);
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(publisherId, book.getPublisher().getId());
        assertEquals(isbn, book.getIsbn());
        assertEquals(publishingYear, book.getPublishingYear().getValue());
    }

    @Test
    @DisplayName("Create book if publisher doesn't exist")
    void shouldReturn400IfPublisherDoesNotExist() throws Exception {
        int notExistingPublisherId = 3;

        String body = """
                {
                    "title": "%s",
                    "author": "%s",
                    "publisherId": %d,
                    "isbn": "%s",
                    "publishingYear": %d
                }
                """.formatted(title, author, notExistingPublisherId, isbn, publishingYear);

        MvcResult mvcResult = mvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();

        ErrorDetails errorDetails = mapper.readValue(json, ErrorDetails.class);

        String expectedMessage = "Illegal argument";
        assertEquals(expectedMessage, errorDetails.getMessage());

        String expectedErrorDetail = "Publisher with id=%d doesn't exist".formatted(notExistingPublisherId);
        assertEquals(expectedErrorDetail, errorDetails.getDetails().get(0));
    }

    @Test
    @DisplayName("Create book if year is in future")
    void shouldReturn400IfYearIsInFuture() throws Exception {
        int notValidPublishingYear = Year.now().getValue() + 1;

        String body = """
                {
                    "title": "%s",
                    "author": "%s",
                    "publisherId": %d,
                    "isbn": "%s",
                    "publishingYear": %d
                }
                """.formatted(title, author, publisherId, isbn, notValidPublishingYear);

        MvcResult mvcResult = mvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();

        ErrorDetails errorDetails = mapper.readValue(json, ErrorDetails.class);

        String expectedMessage = "Illegal argument";
        assertEquals(expectedMessage, errorDetails.getMessage());

        String expectedErrorDetail = "Publishing year should be before now";
        assertEquals(expectedErrorDetail, errorDetails.getDetails().get(0));
    }

    @Test
    @DisplayName("Create book if not valid params")
    void shouldReturn400IfNotValidParamsForSaving() throws Exception {
        String notValidTitle = "";
        String notValidAuthor = "";
        int notValidPublisherId = 0;
        String notValidIsbn = "123";
        Integer notValidPublishingYear = null;

        String body = """
                {
                    "title": "%s",
                    "author": "%s",
                    "publisherId": %d,
                    "isbn": "%s",
                    "publishingYear": %d
                }
                """.formatted(notValidTitle, notValidAuthor, notValidPublisherId, notValidIsbn, notValidPublishingYear);

        MvcResult mvcResult = mvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();

        ErrorDetails errorDetails = mapper.readValue(json, ErrorDetails.class);

        String expectedMessage = "Validation failed";
        assertEquals(expectedMessage, errorDetails.getMessage());

        int expectedCountOfValidationErrors = 5;
        assertEquals(expectedCountOfValidationErrors, errorDetails.getDetails().size());
    }

    @Test
    @DisplayName("Get book by existing id")
    void shouldReturnBookById() throws Exception {
        Book book = new Book(
                null, title, author,
                new Publisher(publisherId, null, null),
                isbn, Year.of(publishingYear)
        );
        int id = bookRepository.save(book);

        MvcResult mvcResult = mvc.perform(get("/books/" + id))
                .andExpect(status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();

        System.out.println(json);

        BookDetailsDto returnedBookDto = mapper.readValue(json, BookDetailsDto.class);

        assertNotNull(returnedBookDto);
        assertEquals(id, returnedBookDto.getId());
        assertEquals(title, returnedBookDto.getTitle());
        assertEquals(author, returnedBookDto.getAuthor());
        assertEquals(publisherId, returnedBookDto.getPublisherId());
        assertEquals("Zhupansky Publisher", returnedBookDto.getPublisherName());
        assertEquals(isbn, returnedBookDto.getIsbn());
        assertEquals(Year.of(publishingYear), returnedBookDto.getPublishingYear());
    }

    @Test
    @DisplayName("Get book if such id doesn't exist")
    void shouldReturn404IfIdDoesNotExist() throws Exception {
        int notExistingId = 0;

        MvcResult mvcResult = mvc.perform(get("/books/" + notExistingId))
                .andExpect(status().isNotFound())
                .andReturn();

        String expectedErrorMessage = "Book with id=%d not found".formatted(notExistingId);
        String actualErrorMessage = mvcResult.getResponse().getErrorMessage();

        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    @DisplayName("Update book if it exists")
    void shouldUpdateExistingBook() throws Exception {
        Book book = new Book(
                null, title, author,
                new Publisher(publisherId, null, null),
                isbn, Year.of(publishingYear)
        );
        int id = bookRepository.save(book);

        int newPublisherId = 1;
        String newIsbn = "978-617-548-008-3";

        String body = """
                {
                    "title": "%s",
                    "author": "%s",
                    "publisherId": %d,
                    "isbn": "%s",
                    "publishingYear": %d
                }
                """.formatted(title, author, newPublisherId, newIsbn, publishingYear);

        MvcResult mvcResult = mvc.perform(put("/books/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andReturn();

        String expectedResponse = "Successfully updated";
        String actualResponse = mvcResult.getResponse().getContentAsString();

        assertEquals(expectedResponse, actualResponse);

        Book updatedBook = bookRepository.getById(id).orElse(null);

        assertNotNull(updatedBook);
        assertEquals(title, updatedBook.getTitle());
        assertEquals(author, updatedBook.getAuthor());
        assertEquals(newPublisherId, updatedBook.getPublisher().getId());
        assertEquals(newIsbn, updatedBook.getIsbn());
        assertEquals(Year.of(publishingYear), updatedBook.getPublishingYear());
    }

    @Test
    @DisplayName("Update book if it doesn't exist yet")
    void shouldCreateBookIfItDoesNotExist() throws Exception {
        int id = 1;

        String body = """
                {
                    "title": "%s",
                    "author": "%s",
                    "publisherId": %d,
                    "isbn": "%s",
                    "publishingYear": %d
                }
                """.formatted(title, author, publisherId, isbn, publishingYear);

        MvcResult mvcResult = mvc.perform(put("/books/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andReturn();

        int expectedResponse = id;
        int actualResponse = Integer.parseInt(mvcResult.getResponse().getContentAsString());

        assertEquals(expectedResponse, actualResponse);

        Book savedBook = bookRepository.getById(id).orElse(null);

        assertNotNull(savedBook);
        assertEquals(title, savedBook.getTitle());
        assertEquals(author, savedBook.getAuthor());
        assertEquals(publisherId, savedBook.getPublisher().getId());
        assertEquals(isbn, savedBook.getIsbn());
        assertEquals(Year.of(publishingYear), savedBook.getPublishingYear());
    }

    @Test
    @DisplayName("Update book if not valid params")
    void shouldReturn400IfNotValidParamsForUpdate() throws Exception {
        int anyId = 1;

        String notValidTitle = "";
        String notValidAuthor = "";
        int notValidPublisherId = 0;
        String notValidIsbn = "123";
        Integer notValidPublishingYear = null;

        String body = """
                {
                    "title": "%s",
                    "author": "%s",
                    "publisherId": %d,
                    "isbn": "%s",
                    "publishingYear": %d
                }
                """.formatted(notValidTitle, notValidAuthor, notValidPublisherId, notValidIsbn, notValidPublishingYear);

        MvcResult mvcResult = mvc.perform(put("/books/" + anyId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();

        ErrorDetails errorDetails = mapper.readValue(json, ErrorDetails.class);

        String expectedMessage = "Validation failed";
        assertEquals(expectedMessage, errorDetails.getMessage());

        int expectedCountOfValidationErrors = 5;
        assertEquals(expectedCountOfValidationErrors, errorDetails.getDetails().size());
    }

    @Test
    @DisplayName("Delete book with existing id")
    void shouldDeleteBook() throws Exception {
        Book book = new Book(
                null, title, author,
                new Publisher(publisherId, null, null),
                isbn, Year.of(publishingYear)
        );
        int id = bookRepository.save(book);

        MvcResult mvcResult = mvc.perform(delete("/books/" + id))
                .andExpect(status().isOk())
                .andReturn();

        String expectedMessage = "Successfully deleted";
        String actualMessage = mvcResult.getResponse().getErrorMessage();

        assertEquals(expectedMessage, actualMessage);

        assertNull(bookRepository.getById(id).orElse(null));
    }

    @Test
    @DisplayName("Delete book with non-existing id")
    void shouldReturn404IfDeleteWrongId() throws Exception {
        int notExistingId = 0;

        MvcResult mvcResult = mvc.perform(delete("/books/" + notExistingId))
                .andExpect(status().isNotFound())
                .andReturn();

        String expectedErrorMessage = "Couldn't delete: book with id=%d doesn't exist".formatted(notExistingId);
        String actualErrorMessage = mvcResult.getResponse().getErrorMessage();

        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    @DisplayName("Search existing book")
    void shouldReturnFoundedBooks() throws Exception {
        Book book = new Book(
                null, title, author,
                new Publisher(publisherId, null, null),
                isbn, Year.of(publishingYear)
        );
        bookRepository.save(book);

        book.setAuthor("Stephen King");
        book.setPublishingYear(Year.of(2014));
        bookRepository.save(book);

        String body = """
                {
                    "author": "%s",
                    "publishingYear": %d,
                    "from": %d,
                    "size": %d
                }
                """.formatted(author, publishingYear, 0, 10);

        MvcResult mvcResult = mvc.perform(post("/books/_search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();

        BookInfoDto[] returnedBooks = mapper.readValue(json, BookInfoDto[].class);

        assertEquals(1, returnedBooks.length);
        assertTrue(Arrays.stream(returnedBooks)
                         .allMatch(b -> b.getAuthor().equals(author)
                                        && b.getPublishingYear().getValue() == publishingYear)
        );
    }

    @Test
    @DisplayName("Search non-existing book")
    void shouldReturnEmptyListIfBooksNotFoundBySearch() throws Exception {
        String body = """
                {
                    "author": "%s",
                    "publishingYear": %d,
                    "from": %d,
                    "size": %d
                }
                """.formatted(author, publishingYear, 0, 10);

        MvcResult mvcResult = mvc.perform(post("/books/_search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();

        BookInfoDto[] returnedBooks = mapper.readValue(json, BookInfoDto[].class);
        assertEquals(0, returnedBooks.length);
    }

    @Test
    @DisplayName("Search with not valid params")
    void shouldReturn400IfSearchingParamsNotValid() throws Exception {
        String notValidAuthor = "";
        Integer notValidPublishingYear = null;
        int notValidFrom = -1;
        int notValidSize = 0;

        String body = """
                {
                    "author": "%s",
                    "publishingYear": %d,
                    "from": %d,
                    "size": %d
                }
                """.formatted(notValidAuthor, notValidPublishingYear, notValidFrom, notValidSize);

        MvcResult mvcResult = mvc.perform(post("/books/_search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();

        ErrorDetails errorDetails = mapper.readValue(json, ErrorDetails.class);

        String expectedMessage = "Validation failed";
        assertEquals(expectedMessage, errorDetails.getMessage());

        int expectedCountOfValidationErrors = 4;
        assertEquals(expectedCountOfValidationErrors, errorDetails.getDetails().size());
    }
}