package dev.profitsoft.beforbooks.controllers;

import dev.profitsoft.beforbooks.dtos.BookDetailsDto;
import dev.profitsoft.beforbooks.dtos.BookSaveDto;
import dev.profitsoft.beforbooks.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/books")
public class BookController {
    private final BookService booksService;

    @Autowired
    public BookController(BookService booksService) {
        this.booksService = booksService;
    }

    @GetMapping
    public List<BookDetailsDto> getAllBooks() {
        return booksService.getAllBooks();
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
}