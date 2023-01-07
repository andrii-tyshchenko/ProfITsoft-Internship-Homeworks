package dev.profitsoft.hw5.controllers;

import dev.profitsoft.hw5.dtos.BookDetailsDto;
import dev.profitsoft.hw5.dtos.BookInfoDto;
import dev.profitsoft.hw5.dtos.BookQueryDto;
import dev.profitsoft.hw5.dtos.BookSaveDto;
import dev.profitsoft.hw5.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService booksService;

    @Autowired
    public BookController(BookService booksService) {
        this.booksService = booksService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createBook(@Valid @RequestBody BookSaveDto bookDto) {
        return booksService.createBook(bookDto);
    }

    @GetMapping("/{id}")
    public BookDetailsDto getBook(@PathVariable int id) {
        return booksService.getBookById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable int id, @Valid @RequestBody BookSaveDto bookDto) {
        if (booksService.existsWithId(id)) {
            booksService.updateBook(id, bookDto);

            return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
        } else {
            int savedBookId = booksService.createBook(bookDto);

            return new ResponseEntity<>(savedBookId, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Successfully deleted")
    public void deleteBook(@PathVariable int id) {
        booksService.deleteBook(id);
    }

    @PostMapping("/_search")
    public List<BookInfoDto> search(@Valid @RequestBody BookQueryDto query) {
        return booksService.searchBooks(query);
    }
}