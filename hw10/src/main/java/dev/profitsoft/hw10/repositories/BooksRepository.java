package dev.profitsoft.hw10.repositories;

import dev.profitsoft.hw10.data.BookData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BooksRepository extends MongoRepository<BookData, String> {}