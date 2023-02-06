package ru.alxstn.irbisnews.service;

import org.springframework.data.domain.Page;
import ru.alxstn.irbisnews.dto.NewsDTO;


public interface NewsService {
    Page<NewsDTO> findNews(String source, String topic, int page, int count);
}
