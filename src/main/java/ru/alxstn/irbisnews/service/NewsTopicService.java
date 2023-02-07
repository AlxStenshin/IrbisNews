package ru.alxstn.irbisnews.service;

import ru.alxstn.irbisnews.dto.NewsTopicDTO;

import java.util.List;

public interface NewsTopicService {
    List<NewsTopicDTO> findAllTopics();

}
