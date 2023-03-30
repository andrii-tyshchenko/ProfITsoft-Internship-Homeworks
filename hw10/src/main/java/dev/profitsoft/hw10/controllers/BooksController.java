package dev.profitsoft.hw10.controllers;

import dev.profitsoft.hw10.data.UserData;
import dev.profitsoft.hw10.dtos.BookBasicInfo;
import dev.profitsoft.hw10.dtos.BookSaveDto;
import dev.profitsoft.hw10.exceptions.BookNotFoundException;
import dev.profitsoft.hw10.services.BooksService;
import dev.profitsoft.hw10.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final UserService userService;

    @Autowired
    public BooksController(BooksService booksService, UserService userService) {
        this.booksService = booksService;
        this.userService = userService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('PRIV_BOOK_MANAGEMENT')")
    public String addBook(@RequestBody BookSaveDto bookSaveDto) {
        return booksService.saveBook(bookSaveDto);
    }

    @GetMapping("/myBooks")
    public List<BookBasicInfo> getMyBooks() {
        UserData user = userService.findUserByEmail(getUserEmail());

        List<BookBasicInfo> books = new ArrayList<>();

        for (String id : user.getBooksIds()) {
            try {
                books.add(booksService.getBookById(id));
            } catch (BookNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }

        return books;
    }

    @GetMapping("/myBooks/{id}/text")
    public String getTextOfCertainBook(@PathVariable String id) {
        UserData user = userService.findUserByEmail(getUserEmail());

        try {
            if (user.getBooksIds().contains(id)) {
                return booksService.getTextOfBookById(id);
            } else {
                return "This book doesn't belong to you or doesn't exist";
            }
        } catch (BookNotFoundException e) {
            return e.getMessage();
        }
    }

    private String getUserEmail() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof User) {
            return ((User) principal).getUsername();
        } else {
            return "Something went wrong";
        }
    }
}