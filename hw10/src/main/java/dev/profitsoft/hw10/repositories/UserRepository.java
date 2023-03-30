package dev.profitsoft.hw10.repositories;

import dev.profitsoft.hw10.data.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserRepository extends MongoRepository<UserData, String> {
    Optional<UserData> findById(String id);

    List<UserData> findByEmail(String email);

    List<UserData> deleteByEmail(String email);
}