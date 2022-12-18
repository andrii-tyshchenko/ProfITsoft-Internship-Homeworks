package dev.profitsoft.hw4.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class LogoutControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Logout")
    void sillyTestForLogout() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(view().name("redirect:/login"));

        mockMvc.perform(post("/logout"))
                .andExpect(view().name("redirect:/login"));
    }
}