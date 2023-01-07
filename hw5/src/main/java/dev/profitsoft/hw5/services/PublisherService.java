package dev.profitsoft.hw5.services;

import dev.profitsoft.hw5.dtos.PublisherDetailsDto;

import java.util.List;

public interface PublisherService {
    /**
     * Returns all publishers, converted to {@code PublisherDetailsDto} objects.
     * @return list of all publishers, which represented as {@code PublisherDetailsDto} objects
     */
    List<PublisherDetailsDto> getAllPublishers();
}