package dev.profitsoft.hw4.controllers;

import dev.profitsoft.hw4.entities.User;
import dev.profitsoft.hw4.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class UsersControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get users page")
    void sillyTestForGetUsersPage() throws Exception {
        UserRepository userRepository = new UserRepository();

        List<User> users = userRepository.getUsers();

        mockMvc.perform(get("/users")
                        .sessionAttr("current_user", new User("userenko", "User Userenko", "qwerty")))
                .andExpect(view().name("users"))
                .andExpect(model().attribute("users", users));
    }
}