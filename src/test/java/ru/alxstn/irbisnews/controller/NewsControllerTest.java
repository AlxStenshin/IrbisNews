package ru.alxstn.irbisnews.controller;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.alxstn.irbisnews.controller.rest.NewsController;
import ru.alxstn.irbisnews.entity.NewsSource;
import ru.alxstn.irbisnews.entity.NewsTopic;
import ru.alxstn.irbisnews.repository.NewsRepository;
import ru.alxstn.irbisnews.repository.NewsSourceRepository;
import ru.alxstn.irbisnews.repository.NewsTopicRepository;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class NewsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    NewsController controller;

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    NewsSourceRepository newsSourceRepository;

    @Autowired
    NewsTopicRepository newsTopicRepository;

    @AfterEach
    void cleanup() {
        newsSourceRepository.deleteAll();
        newsTopicRepository.deleteAll();
        newsRepository.deleteAll();
    }

    @Test
    void shouldCorrectlyInjectController() {
        assertThat(controller).isNotNull();
    }

    @Test
    void shouldReceiveOkWithCorrectToken() throws Exception {
        mockMvc.perform(get("/api/v1/news")
                .header("token" , "test-token"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReceiveForbiddenStatusWithIncorrectToken() throws Exception {
        mockMvc.perform(get("/api/v1/news")
                .header("token" , "bad-token"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldReceiveNotFoundWithUnavailableSource() throws Exception {
        mockMvc.perform(get("/api/v1/news")
                        .header("token", "test-token")
                        .param("source", "unavailable"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReceiveNotFoundWithUnavailableTopic() throws Exception {
        mockMvc.perform(get("/api/v1/news")
                        .header("token" , "test-token")
                        .param("topic", "unavailable"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReceiveOkWithAvailableTopic() throws Exception {
        newsTopicRepository.save(new NewsTopic(1L, "topic"));

        mockMvc.perform(get("/api/v1/news")
                        .header("token" , "test-token")
                        .param("topic", "topic"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReceiveOkWithAvailableSource() throws Exception {
        newsSourceRepository.save(new NewsSource("source"));

        mockMvc.perform(get("/api/v1/news")
                        .header("token", "test-token")
                        .param("source", "source"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReceiveOkWithBothAvailableSourceAndTopic() throws Exception {
        newsTopicRepository.save(new NewsTopic(1L, "topic"));
        newsSourceRepository.save(new NewsSource("source"));

        mockMvc.perform(get("/api/v1/news")
                        .header("token", "test-token")
                        .param("topic", "topic")
                        .param("source", "source"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}