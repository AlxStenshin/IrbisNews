package ru.alxstn.irbisnews.service;

import org.springframework.stereotype.Service;
import ru.alxstn.irbisnews.dto.NewsTopicDTO;
import ru.alxstn.irbisnews.dto.builder.NewsTopicDTOBuilder;
import ru.alxstn.irbisnews.repository.NewsTopicRepository;

import java.util.List;

@Service
public class BasicNewsTopicService implements NewsTopicService {

    private final NewsTopicRepository repository;
    private final NewsTopicDTOBuilder dtoBuilder;

    public BasicNewsTopicService(NewsTopicRepository repository, NewsTopicDTOBuilder dtoBuilder) {
        this.repository = repository;
        this.dtoBuilder = dtoBuilder;
    }

    @Override
    public List<NewsTopicDTO> findAllTopics() {
        return repository.findAll().stream().map(dtoBuilder::fromNewsTopic).toList();
    }

}
