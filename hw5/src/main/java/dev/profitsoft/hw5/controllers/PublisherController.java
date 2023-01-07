package dev.profitsoft.hw5.controllers;

import dev.profitsoft.hw5.dtos.PublisherDetailsDto;
import dev.profitsoft.hw5.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public List<PublisherDetailsDto> getAllPublishers() {
        return publisherService.getAllPublishers();
    }
}