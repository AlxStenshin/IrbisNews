package ru.alxstn.irbisnews.service;

import org.springframework.data.domain.Page;
import ru.alxstn.irbisnews.entity.news.NewsEntry;


public interface NewsService {

    Page<NewsEntry> findNews(String source, String topic, int page, int count);

}
