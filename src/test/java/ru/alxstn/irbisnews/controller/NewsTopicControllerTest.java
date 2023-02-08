package ru.alxstn.irbisnews.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.alxstn.irbisnews.controller.rest.NewsTopicController;
import ru.alxstn.irbisnews.entity.NewsTopic;
import ru.alxstn.irbisnews.repository.NewsTopicRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class NewsTopicControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    NewsTopicRepository repository;

    @Autowired
    NewsTopicController controller;

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
        var result = mockMvc.perform(get("/api/v1/topic")
                        .header("token", "test-token"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("[]", result.getResponse().getContentAsString());
    }

    @Test
    void shouldReturnListOfTwoSources() throws Exception {
        List<NewsTopic> input = List.of(
                new NewsTopic(1L,"one"),
                new NewsTopic(2L,"two")
        );

        repository.saveAll(input);

        var result = mockMvc.perform(get("/api/v1/topic")
                        .header("token", "test-token"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        for (var topic : input) {
            System.out.println(topic.getTitle());
            assertTrue(result.getResponse().getContentAsString().contains(topic.getTitle()));
        }
    }

}