package dev.profitsoft.hw10.repositories;

import dev.profitsoft.hw10.data.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class RoleRepository {
    private static final Map<String, Role> ROLES = Stream.of(
                    new Role("USER", List.of()),
                    new Role("ADMIN", List.of("USER_MANAGEMENT", "BOOK_MANAGEMENT"))
            )
            .collect(Collectors.toUnmodifiableMap(
                    Role::getId,
                    Function.identity()
                    )
            );

    public Optional<Role> getRole(String id) {
        return Optional.ofNullable(ROLES.get(id));
    }
}