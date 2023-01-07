package dev.profitsoft.hw5.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Year;

public class BookQueryDto {
    @NotBlank(message = "author is required")
    private String author;
    @NotNull
    private Year publishingYear;
    /**
     * Quantity of books, that should be skipped (shouldn't be included in returned result).
     * NB: numeration starts from 0
     */
    @Min(value = 0, message = "should be equal or greater than 0")
    private Integer from;
    /**
     * Quantity of books, that should be returned
     */
    @Min(value = 1, message = "should be greater than 1")
    private Integer size;

    public BookQueryDto(String author, Year publishingYear, Integer from, Integer size) {
        this.author = author;
        this.publishingYear = publishingYear;
        this.from = from;
        this.size = size;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Year getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(Year publishingYear) {
        this.publishingYear = publishingYear;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}