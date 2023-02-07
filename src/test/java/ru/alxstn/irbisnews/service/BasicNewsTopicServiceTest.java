package ru.alxstn.irbisnews.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.alxstn.irbisnews.dto.NewsTopicDTO;
import ru.alxstn.irbisnews.dto.builder.NewsTopicDTOBuilder;
import ru.alxstn.irbisnews.entity.NewsTopic;
import ru.alxstn.irbisnews.repository.NewsTopicRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BasicNewsTopicServiceTest {

    @Mock
    NewsTopicRepository newsTopicRepository;

    @Mock
    NewsTopicDTOBuilder dtoBuilder;

    @InjectMocks
    BasicNewsTopicService topicService;

    @Test
    void shouldReturnEmptyListWithEmptyRepository() {
        when(newsTopicRepository.findAll()).thenReturn(Collections.emptyList());
        List<NewsTopicDTO> result = topicService.findAllTopics();
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnTwoElementsList() {
        var topic1 = new NewsTopic(1L, "topic1");
        var topic2 = new NewsTopic(2L, "topic2");
        var expected = List.of(topic1, topic2);
        when(newsTopicRepository.findAll()).thenReturn(expected);
        when(dtoBuilder.fromNewsTopic(topic1)).thenReturn(new NewsTopicDTO(topic1.getId(), topic1.getTitle()));
        when(dtoBuilder.fromNewsTopic(topic2)).thenReturn(new NewsTopicDTO(topic2.getId(), topic2.getTitle()));

        List<NewsTopicDTO> result = topicService.findAllTopics();

        assertFalse(result.isEmpty());
        assertEquals(expected.size(), result.size());
    }

}