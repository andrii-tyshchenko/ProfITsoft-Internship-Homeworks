package dev.profitsoft.hw8.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.profitsoft.hw8.Hw8Application;
import dev.profitsoft.hw8.data.PEPData;
import dev.profitsoft.hw8.dtos.PEPBasicInfoDto;
import dev.profitsoft.hw8.dtos.PEPsTopFirstNamesDto;
import dev.profitsoft.hw8.repositories.PEPsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Hw8Application.class
)
@AutoConfigureMockMvc
class PEPsControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private PEPsRepository pepsRepository;

    private String firstName = "Тарас";
    private String patronymic = "Григорович";
    private String lastName = "Шевченко";

    @AfterEach
    void afterEach() {
        pepsRepository.deleteAll();
    }

    @Test
    @DisplayName("Search existing PEP by full name")
    void shouldReturnPEPsFoundByNamePatronymicAndSurname() throws Exception {
        add5TestPEPsToDB();

        String body = """
                {
                   "firstName": "%s",
                   "patronymic": "%s",
                   "lastName": "%s"
                }
                """.formatted(firstName, patronymic, lastName);

        MockHttpServletResponse response = mvc.perform(post("/peps/_search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        String json = response.getContentAsString(StandardCharsets.UTF_8);

        PEPBasicInfoDto[] peps = mapper.readValue(json, PEPBasicInfoDto[].class);

        assertEquals(1, peps.length);
        assertEquals(firstName, peps[0].getFirstNameUA());
        assertEquals(patronymic, peps[0].getPatronymic());
        assertEquals(lastName, peps[0].getLastNameUA());
    }

    @Test
    @DisplayName("Search non-existing PEP by full name")
    void shouldReturnEmptyArrayIfPEPNotFoundByNamePatronymicAndSurname() throws Exception {
        add5TestPEPsToDB();

        String body = """
                {
                   "firstName": "Андрій",
                   "patronymic": "%s",
                   "lastName": "%s"
                }
                """.formatted(patronymic, lastName);

        MockHttpServletResponse response = mvc.perform(post("/peps/_search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        String json = response.getContentAsString(StandardCharsets.UTF_8);

        PEPBasicInfoDto[] peps = mapper.readValue(json, PEPBasicInfoDto[].class);

        assertEquals(0, peps.length);
    }

    @Test
    @DisplayName("Get most popular first names + their quantity")
    void shouldReturnTopPEPNamesAndTheirQuantityWhereIsPepTrue() throws Exception {
        add5TestPEPsToDB();

        MockHttpServletResponse response = mvc.perform(get("/peps/top_first_names"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        String json = response.getContentAsString(StandardCharsets.UTF_8);

        PEPsTopFirstNamesDto[] peps = mapper.readValue(json, PEPsTopFirstNamesDto[].class);

        assertEquals(2, peps.length);
        assertEquals(firstName, peps[0].getFirstName());
        assertEquals(2, peps[0].getQuantity());
        assertEquals("Володимир", peps[1].getFirstName());
        assertEquals(1, peps[1].getQuantity());
    }

    @Test
    @DisplayName("Get most popular names if no PEP has \"is_pep\" true")
    void shouldReturnEmptyArrayIfNoPEPHasIsPepTrue() throws Exception {
        PEPData pep = new PEPData();
        pep.setFirstNameUA(firstName);
        pep.setIsPEP(false);
        pepsRepository.save(pep);

        MockHttpServletResponse response = mvc.perform(get("/peps/top_first_names"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        String json = response.getContentAsString(StandardCharsets.UTF_8);

        PEPsTopFirstNamesDto[] peps = mapper.readValue(json, PEPsTopFirstNamesDto[].class);

        assertEquals(0, peps.length);
    }

    private void add5TestPEPsToDB() {
        PEPData pep1 = new PEPData();
        pep1.setFirstNameUA(firstName);
        pep1.setPatronymic(patronymic);
        pep1.setLastNameUA(lastName);
        pep1.setIsPEP(false);
        pepsRepository.save(pep1);

        PEPData pep2 = new PEPData();
        pep2.setFirstNameUA(firstName);
        pep2.setPatronymic(patronymic);
        pep2.setLastNameUA("Кльофа");
        pep2.setIsPEP(true);
        pepsRepository.save(pep2);

        PEPData pep3 = new PEPData();
        pep3.setFirstNameUA(firstName);
        pep3.setPatronymic("Миколайович");
        pep3.setLastNameUA("Висоцький");
        pep3.setIsPEP(true);
        pepsRepository.save(pep3);

        PEPData pep4 = new PEPData();
        pep4.setFirstNameUA("Володимир");
        pep4.setPatronymic("Іванович");
        pep4.setLastNameUA("Висоцький");
        pep4.setIsPEP(true);
        pepsRepository.save(pep4);

        PEPData pep5 = new PEPData();
        pep5.setFirstNameUA("Володимир");
        pep5.setPatronymic("Семенович");
        pep5.setLastNameUA("Висоцький");
        pep5.setIsPEP(false);
        pepsRepository.save(pep5);
    }
}