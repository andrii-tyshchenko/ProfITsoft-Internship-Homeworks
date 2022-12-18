package dev.profitsoft.hw4.controllers;

import dev.profitsoft.hw4.entities.User;
import dev.profitsoft.hw4.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserRepository userRepository;

    @Autowired
    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping()
    public ModelAndView getUsersPage() {
        List<User> users = userRepository.getUsers();

        return new ModelAndView("users")
                .addObject("users", users);
    }
}