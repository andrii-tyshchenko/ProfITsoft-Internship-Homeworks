package dev.profitsoft.hw4.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/logout")
public class LogoutController {
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView logout(HttpSession session) {
        session.invalidate();

        return new ModelAndView("redirect:/login");
    }
}