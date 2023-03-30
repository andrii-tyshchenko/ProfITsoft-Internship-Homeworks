package dev.profitsoft.hw10.services;

import dev.profitsoft.hw10.dtos.BookBasicInfo;
import dev.profitsoft.hw10.dtos.BookSaveDto;
import dev.profitsoft.hw10.exceptions.BookNotFoundException;

public interface BooksService {
    String saveBook(BookSaveDto bookDto);

    BookBasicInfo getBookById(String id) throws BookNotFoundException;

    String getTextOfBookById(String id) throws BookNotFoundException;
}