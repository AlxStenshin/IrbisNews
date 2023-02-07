package ru.alxstn.irbisnews.service;

import ru.alxstn.irbisnews.dto.NewsSourceDTO;

import java.util.List;

public interface NewsSourceService {
    List<NewsSourceDTO> findAllSources();
}
