package ru.alxstn.irbisnews.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.alxstn.irbisnews.entity.news.NewsTopic;
import ru.alxstn.irbisnews.repository.NewsTopicRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class NewsTopicControllerTest {

    @Mock
    NewsTopicRepository repository;

    @InjectMocks
    NewsTopicController controller;

    @Test
    void shouldReturnEmptyListWithEmptyRepository() {
        when(repository.findAll()).thenReturn(List.of());
        var result = controller.getAllTopics();
        assertEquals(0, result.size());

    }

    @Test
    void shouldReturnListOfFourTopics() {
        List<String> input = List.of(
                "Помощь юр. лицам",
                "Помощь физ. лицам",
                "О нас",
                "Обновления сервиса");
        List<NewsTopic> topics = input.stream().map(NewsTopic::new).toList();
        when(repository.findAll()).thenReturn(topics);

        var result = controller.getAllTopics();

        assertEquals(input.size(), result.size());
        assertTrue(result.stream().allMatch(ns -> input.contains(ns.getTitle())));
    }

}