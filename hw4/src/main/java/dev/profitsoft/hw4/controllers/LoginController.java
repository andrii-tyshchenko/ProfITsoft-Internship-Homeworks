package dev.profitsoft.hw4.controllers;

import dev.profitsoft.hw4.entities.User;
import dev.profitsoft.hw4.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ModelAndView getLoginPage() {
        return new ModelAndView("login");
    }

    @PostMapping
    public ModelAndView login(HttpSession session, User user, RedirectAttributes redirectAttributes) {
        User currentUser = userRepository.getUser(user);

        if (currentUser == null || user.getLogin() == null || user.getPassword() == null) {
            redirectAttributes.addFlashAttribute("error", "Wrong credentials!");

            return new ModelAndView("redirect:/login");
        } else {
            session.setAttribute("current_user", currentUser);

            return new ModelAndView("redirect:/homepage");
        }
    }
}