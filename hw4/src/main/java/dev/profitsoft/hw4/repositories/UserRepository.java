package dev.profitsoft.hw4.repositories;

import dev.profitsoft.hw4.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;

//TODO: javadoc

/**
 * Repository for users
 */
@Repository
public class UserRepository {
    /**
     * Returns all users.
     * @return list of all users
     */
    public List<User> getUsers() {
        return List.of(
                new User("userenko", "User Userenko", "qwerty"),
                new User("johndoe", "John Doe", "12345"),
                new User("mr_smith", "Mr.Smith", "bangbang")
        );
    }

    /**
     * Returns either user, which has the same login and password, as given one, or null (if such user not found).
     * @param user [for purposes of this task:] user received from POST-request
     * @return user with the same login and password as given one or null
     */
    public User getUser(User user) {
        return getUsers().stream()
                .filter(u -> u.getLogin().equals(user.getLogin()) && u.getPassword().equals(user.getPassword()))
                .findFirst()
                .orElse(null);
    }
}