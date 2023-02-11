package dev.profitsoft.beforbooks.dtos;

import java.time.Year;

public class BookDetailsDto {
    private Integer id;
    private String title;
    private String author;
    private Integer publisherId;
    private String publisherName;
    private String isbn;
    private Year publishingYear;

    public BookDetailsDto() {
    }

    public BookDetailsDto(Integer id, String title, String author, Integer publisherId,
                          String publisherName, String isbn, Year publishingYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisherId = publisherId;
        this.publisherName = publisherName;
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

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
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