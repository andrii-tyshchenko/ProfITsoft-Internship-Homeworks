package dev.profitsoft.hw4.repositories;

import dev.profitsoft.hw4.entities.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserRepositoryTest {
    UserRepository userRepository = new UserRepository();

    @Test
    @DisplayName("Get all users not null")
    void getUsersNotNull() {
        assertNotNull(userRepository.getUsers());
    }

    @Test
    @DisplayName("Get non-existing user should be null")
    void getUserIfNotExists() {
        assertNull(userRepository.getUser(new User(null, null, null)));
    }
}