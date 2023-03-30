package dev.profitsoft.hw10.controllers;

import dev.profitsoft.hw10.data.UserData;
import dev.profitsoft.hw10.dtos.UserSaveDto;
import dev.profitsoft.hw10.exceptions.UserIdNotFoundException;
import dev.profitsoft.hw10.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('PRIV_USER_MANAGEMENT')")
    public String addUser(@RequestBody UserSaveDto userSaveDto) {
        if (userSaveDto.getRole().equals("ADMIN")) {
            return "You can't create ADMIN users!";
        }

        if (userService.existsWithEmail(userSaveDto.getEmail())) {
            return "User with email " + userSaveDto.getEmail() + " already exists!";
        }

        return userService.saveUser(userSaveDto);
    }

    @PostMapping("/{userId}/addBooks")
    @PreAuthorize("hasAuthority('PRIV_USER_MANAGEMENT')")
    public String addBooksToUser(@PathVariable String userId, @RequestBody List<String> booksIds) {
        try {
            UserData user = userService.findUserById(userId);

            // додаємо нові книги до вже наявних
            user.setBooksIds(Stream.concat(user.getBooksIds().stream(), booksIds.stream()).toList());

            return userService.updateUser(user);
        } catch (UserIdNotFoundException e) {
            return e.getMessage();
        }
    }
}