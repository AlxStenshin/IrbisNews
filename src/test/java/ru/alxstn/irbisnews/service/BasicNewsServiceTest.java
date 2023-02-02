package ru.alxstn.irbisnews.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.alxstn.irbisnews.exception.NewsSourceNotFoundException;
import ru.alxstn.irbisnews.exception.NewsTopicNotFoundException;
import ru.alxstn.irbisnews.repository.NewsRepository;
import ru.alxstn.irbisnews.repository.NewsSourceRepository;
import ru.alxstn.irbisnews.repository.NewsTopicRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BasicNewsServiceTest {

    @Mock
    NewsRepository newsRepository;

    @Mock
    NewsSourceRepository newsSourceRepository;

    @Mock
    NewsTopicRepository newsTopicRepository;

    @InjectMocks
    BasicNewsService newsService;

    @Test
    void shouldThrowSourceNotFoundException() {
        when(newsSourceRepository.findByName(any(String.class))).thenReturn(Optional.empty());
        assertThrows(NewsSourceNotFoundException.class,
                () -> newsService.findNews("source", null, 0, 1));
    }

    @Test
    void shouldThrowTopicNotFoundException() {
        when(newsTopicRepository.findByTitle(any(String.class))).thenReturn(Optional.empty());
        assertThrows(NewsTopicNotFoundException.class,
                () -> newsService.findNews(null, "topic", 0, 1));
    }

}