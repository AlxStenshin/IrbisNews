package ru.alxstn.irbisnews.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.alxstn.irbisnews.controller.rest.NewsSourceController;
import ru.alxstn.irbisnews.entity.NewsSource;
import ru.alxstn.irbisnews.repository.NewsSourceRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class NewsSourceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    NewsSourceRepository repository;

    @Autowired
    NewsSourceController controller;

    @AfterEach
    void cleanup() {
        repository.deleteAll();
    }

    @Test
    void shouldCorrectlyInjectBeans() {
        assertThat(controller).isNotNull();
        assertThat(repository).isNotNull();
    }

    @Test
    void shouldReturnEmptyListWithEmptyRepository() throws Exception {
        var result = mockMvc.perform(get("/api/v1/source")
                        .header("token", "test-token"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("[]", result.getResponse().getContentAsString());

    }

    @Test
    void shouldReturnListOfTwoSources() throws Exception {
        List<NewsSource> input = List.of(
                new NewsSource("irbis.plus"),
                new NewsSource("praktika.irbis.plus")
        );

        repository.saveAll(input);

        var result =  mockMvc.perform(get("/api/v1/source")
                        .header("token", "test-token"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        for (var source: input) {
            assertTrue(result.getResponse().getContentAsString().contains(source.getName()));
        }
    }

}