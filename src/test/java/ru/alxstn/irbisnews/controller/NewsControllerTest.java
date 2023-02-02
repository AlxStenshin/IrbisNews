package ru.alxstn.irbisnews.controller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.alxstn.irbisnews.service.NewsService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(value = SpringExtension.class)
class NewsControllerTest {

    @Mock
    NewsService service;

    @InjectMocks
    NewsController controller;

    @Test
    void shouldCallServiceExactlyOneTime() {
        when(service.findNews("", "", 0, 1)).thenReturn(Page.empty());

        var result = controller.getNews("", "", 0, 1);

        verify(service, times(1)).findNews("", "", 0, 1);
        assertEquals(0, result.getSize());
    }

}