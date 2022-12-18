package dev.profitsoft.hw4.controllers;

import dev.profitsoft.hw4.entities.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get login page")
    void sillyTestForGetLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(view().name("login"));
    }

    @Test
    @DisplayName("Post login page")
    void sillyTestForPostLoginPage() throws Exception {
        mockMvc.perform(post("/login"))
                .andExpect(view().name("redirect:/login"))
                .andExpect(flash().attribute("error", "Wrong credentials!"));

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("login", "userenko")
                        .param("password", "qwerty")
                        .sessionAttr("current_user", new User("userenko", "User Userenko", "qwerty")))
                .andExpect(view().name("redirect:/homepage"));
    }
}
