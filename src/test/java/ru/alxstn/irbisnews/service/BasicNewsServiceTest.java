package ru.alxstn.irbisnews.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.alxstn.irbisnews.dto.NewsDTO;
import ru.alxstn.irbisnews.dto.builder.NewsDTOBuilder;
import ru.alxstn.irbisnews.entity.NewsEntry;
import ru.alxstn.irbisnews.entity.NewsSource;
import ru.alxstn.irbisnews.entity.NewsTopic;
import ru.alxstn.irbisnews.exception.NewsSourceNotFoundException;
import ru.alxstn.irbisnews.exception.NewsTopicNotFoundException;
import ru.alxstn.irbisnews.repository.NewsRepository;
import ru.alxstn.irbisnews.repository.NewsSourceRepository;
import ru.alxstn.irbisnews.repository.NewsTopicRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BasicNewsServiceTest {

    @Mock
    NewsRepository newsRepository;

    @Mock
    NewsSourceRepository newsSourceRepository;

    @Mock
    NewsTopicRepository newsTopicRepository;

    @Mock
    NewsDTOBuilder builder;

    @InjectMocks
    BasicNewsService newsService;


    @Test
    void shouldThrowSourceNotFoundException() {
        when(newsSourceRepository.findByName(any(String.class))).thenReturn(Optional.empty());
        assertThrows(NewsSourceNotFoundException.class,
                () -> newsService.findNews("source", null, 0, 1));

        verify(newsSourceRepository).findByName("source");
    }

    @Test
    void shouldThrowTopicNotFoundException() {
        when(newsTopicRepository.findByTitle(any(String.class))).thenReturn(Optional.empty());
        assertThrows(NewsTopicNotFoundException.class,
                () -> newsService.findNews(null, "topic", 0, 1));

        verify(newsTopicRepository).findByTitle("topic");
    }

    @Test
    void shouldReturnPageWithSingleDTO() {
        Pageable pageable = PageRequest.of(0, 1);
        NewsEntry entry = new NewsEntry(new NewsSource("source"), new NewsTopic("topic"), "content");
        when(newsRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(entry), pageable, 1L));
        when(builder.fromEntry(entry)).thenReturn(new NewsDTO("content", "source", "topic"));

        var actual = newsService.findNews(null, null, 0, 1);

        assertNotNull(actual);
        assertEquals(actual.getTotalElements(), 1);
        assertEquals(actual.getContent().get(0).getContent(), "content");
        assertEquals(actual.getContent().get(0).getSource(), "source");
        assertEquals(actual.getContent().get(0).getTopic(), "topic");
    }

}