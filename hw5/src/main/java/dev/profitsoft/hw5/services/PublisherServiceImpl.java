package dev.profitsoft.hw5.services;

import dev.profitsoft.hw5.dtos.PublisherDetailsDto;
import dev.profitsoft.hw5.repositories.PublisherRepository;
import dev.profitsoft.hw5.entities.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<PublisherDetailsDto> getAllPublishers() {
        return publisherRepository.getAll().stream()
                .map(this::convertToPublisherDetailsDto)
                .toList();
    }

    /**
     * Converts given {@code Publisher} object to {@code PublisherDetailsDto} object.
     * @param publisher object, which should be converted
     * @return {@code PublisherDetailsDto} object with fields values, taken from the given {@code Publisher} object
     */
    private PublisherDetailsDto convertToPublisherDetailsDto(Publisher publisher) {
        return new PublisherDetailsDto(
                publisher.getId(),
                publisher.getName(),
                publisher.getCountry().getId(),
                publisher.getCountry().getName()
        );
    }
}