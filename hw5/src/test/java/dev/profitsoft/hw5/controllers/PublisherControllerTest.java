package dev.profitsoft.hw5.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.profitsoft.hw5.dtos.PublisherDetailsDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class PublisherControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("Get all publishers")
    void testGetAllPublishers() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/publishers"))
                .andExpect(status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();

        //List<PublisherDetailsDto> actualPublishers = mapper.readValue(json, new TypeReference<>(){});
        List<PublisherDetailsDto> actualPublishers = Arrays.asList(mapper.readValue(json, PublisherDetailsDto[].class));

        List<PublisherDetailsDto> expectedPublishers = List.of(
                new PublisherDetailsDto(1, "KSD", 1, "Ukraine"),
                new PublisherDetailsDto(2, "Zhupansky Publisher", 1, "Ukraine")
        );

        assertEquals(expectedPublishers, actualPublishers);
    }
}