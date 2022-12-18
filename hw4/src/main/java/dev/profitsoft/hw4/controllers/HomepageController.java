package dev.profitsoft.hw4.controllers;

import dev.profitsoft.hw4.entities.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/homepage")
public class HomepageController {
    @GetMapping
    public ModelAndView getHomepage(HttpSession session) {
        String userName = ((User) session.getAttribute("current_user")).getName();

        return new ModelAndView("homepage")
                .addObject("user_name", userName);
    }
}