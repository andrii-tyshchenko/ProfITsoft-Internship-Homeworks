package dev.profitsoft.hw5.dtos;

import java.time.Year;

public class BookInfoDto {
    private Integer id;
    private String title;
    private String author;
    private String publisherName;
    private Year publishingYear;

    public BookInfoDto() {
    }

    public BookInfoDto(Integer id, String title, String author, String publisherName, Year publishingYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisherName = publisherName;
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

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public Year getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(Year publishingYear) {
        this.publishingYear = publishingYear;
    }
}