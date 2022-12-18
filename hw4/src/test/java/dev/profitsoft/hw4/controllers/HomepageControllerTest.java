package dev.profitsoft.hw4.controllers;

import dev.profitsoft.hw4.entities.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class HomepageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get homepage")
    void sillyTestForGetHomepage() throws Exception {
        mockMvc.perform(get("/homepage")
                        .sessionAttr("current_user", new User("userenko", "User Userenko", "qwerty")))
                .andExpect(view().name("homepage"))
                .andExpect(model().attribute("user_name", "User Userenko"));
    }
}