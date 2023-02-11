package dev.profitsoft.beforbooks.entities;

import java.time.Year;

/**
 * Represents main information about book.
 * For purposes of this task field "author" is represented as String, not Author object.
 * Also, it is supposed that Book always has an author, and it has only 1 author.
 */
public class Book {
    private Integer id;
    private String title;
    private String author;
    private Publisher publisher;
    private String isbn;
    private Year publishingYear;

    public Book() {
    }

    public Book(Integer id, String title, String author, Publisher publisher,
                String isbn, Year publishingYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.publishingYear = publishingYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Year getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(Year publishingYear) {
        this.publishingYear = publishingYear;
    }
}