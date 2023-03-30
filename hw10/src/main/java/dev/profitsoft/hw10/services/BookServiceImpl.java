package dev.profitsoft.hw10.services;

import dev.profitsoft.hw10.data.BookData;
import dev.profitsoft.hw10.dtos.BookBasicInfo;
import dev.profitsoft.hw10.dtos.BookSaveDto;
import dev.profitsoft.hw10.exceptions.BookNotFoundException;
import dev.profitsoft.hw10.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BookServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public String saveBook(BookSaveDto bookDto) {
        BookData book = convertToBookData(bookDto);

        return booksRepository.insert(book).getId();
    }

    @Override
    public BookBasicInfo getBookById(String id) throws BookNotFoundException {
        BookData bookData = booksRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with id=" + id + " not found"));

        return convertToBookBasicInfo(bookData);
    }

    @Override
    public String getTextOfBookById(String id) throws BookNotFoundException {
        BookData bookData = booksRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with id=" + id + " not found"));

        return bookData.getText();
    }

    private BookData convertToBookData(BookSaveDto bookDto) {
        BookData book = new BookData();

        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPublisher(bookDto.getPublisher());
        book.setIsbn(bookDto.getIsbn());
        book.setPublishingYear(bookDto.getPublishingYear());
        book.setText(bookDto.getText());

        return book;
    }

    private BookBasicInfo convertToBookBasicInfo(BookData bookData) {
        BookBasicInfo bookBasicInfo = new BookBasicInfo();
        bookBasicInfo.setId(bookData.getId());
        bookBasicInfo.setTitle(bookData.getTitle());
        bookBasicInfo.setAuthor(bookData.getAuthor());
        bookBasicInfo.setPublisher(bookData.getPublisher());
        bookBasicInfo.setIsbn(bookData.getIsbn());
        bookBasicInfo.setPublishingYear(bookData.getPublishingYear());

        return bookBasicInfo;
    }
}