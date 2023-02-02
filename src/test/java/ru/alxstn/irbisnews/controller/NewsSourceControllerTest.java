package ru.alxstn.irbisnews.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.alxstn.irbisnews.entity.news.NewsSource;
import ru.alxstn.irbisnews.repository.NewsSourceRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class NewsSourceControllerTest {

    @Mock
    NewsSourceRepository repository;

    @InjectMocks
    NewsSourceController controller;

    @Test
    void shouldReturnEmptyListWithEmptyRepository() {
        when(repository.findAll()).thenReturn(List.of());
        var result = controller.getAllSources();
        assertEquals(0, result.size());
    }

    @Test
    void shouldReturnListOfTwoSources() {
        List<String> input = List.of("irbis.plus", "praktika.irbis.plus");
        List<NewsSource> sources = input.stream().map(NewsSource::new).toList();
        when(repository.findAll()).thenReturn(sources);

        var result = controller.getAllSources();

        assertEquals(input.size(), result.size());
        assertTrue(result.stream().allMatch(ns -> input.contains(ns.getName())));
    }

}