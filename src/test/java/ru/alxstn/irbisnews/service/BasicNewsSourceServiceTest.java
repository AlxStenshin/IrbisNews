package ru.alxstn.irbisnews.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.alxstn.irbisnews.dto.NewsSourceDTO;
import ru.alxstn.irbisnews.dto.builder.NewsSourceDTOBuilder;
import ru.alxstn.irbisnews.entity.NewsSource;
import ru.alxstn.irbisnews.repository.NewsSourceRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BasicNewsSourceServiceTest {

    @Mock
    NewsSourceRepository newsSourceRepository;

    @Mock
    NewsSourceDTOBuilder dtoBuilder;

    @InjectMocks
    BasicNewsSourceService sourceService;

    @Test
    void shouldReturnEmptyListWithEmptyRepository() {
        when(newsSourceRepository.findAll()).thenReturn(Collections.emptyList());
        List<NewsSourceDTO> result = sourceService.findAllSources();
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnTwoElementsList() {
        var source1 = new NewsSource(1L, "source1");
        var source2 = new NewsSource(2L, "source2");
        var expected = List.of(source1, source2);
        when(newsSourceRepository.findAll()).thenReturn(expected);
        when(dtoBuilder.fromNewsSource(source1)).thenReturn(new NewsSourceDTO(source1.getId(), source1.getName()));
        when(dtoBuilder.fromNewsSource(source2)).thenReturn(new NewsSourceDTO(source2.getId(), source2.getName()));

        List<NewsSourceDTO> result = sourceService.findAllSources();

        assertFalse(result.isEmpty());
        assertEquals(expected.size(), result.size());
    }
}