package dev.profitsoft.reader.controllers;

import dev.profitsoft.hw10.dtos.BookBasicInfo;
import dev.profitsoft.reader.MyBooksClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.Base64;
import java.util.List;

@Controller
public class MyBooksController {
    private String userEmail;
    private String userPassword;

    @Autowired
    private MyBooksClient myBooksClient;

    @GetMapping("/myBooks")
    public String home(Model model) {
        String authHeader = createAuthHeader(userEmail, userPassword);

        try {
            List<BookBasicInfo> myBooks = myBooksClient.getMyBooks(authHeader);

            model.addAttribute("myBooks", myBooks);
        } catch (ResponseStatusException e) {
            model.addAttribute("error", e.getStatus());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        return "myBooks";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginToLibrary(Model model, String email, String password) {
        String authHeader = createAuthHeader(email, password);

        try {
            myBooksClient.getMyBooks(authHeader);

            userEmail = email;
            userPassword = password;

            return "redirect:/myBooks";
        } catch (ResponseStatusException e) {
            model.addAttribute("error", e.getStatus());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        return "/login";
    }

    @GetMapping("/myBooks/{id}")
    public String getTextOfMyBook(@PathVariable String id, Model model) {
        String authHeader = createAuthHeader(userEmail, userPassword);

        try {
            String textOfTheBook = myBooksClient.getTextOfCertainBook(id, authHeader);

            model.addAttribute("textOfTheBook", textOfTheBook);
        } catch (ResponseStatusException e) {
            model.addAttribute("error", e.getStatus());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        return "readTextOfTheBook";
    }

    private String createAuthHeader(String email, String password) {
        byte[] encodedBytes = Base64.getEncoder().encode((email + ":" + password).getBytes());

        return "Basic " + new String(encodedBytes);
    }
}