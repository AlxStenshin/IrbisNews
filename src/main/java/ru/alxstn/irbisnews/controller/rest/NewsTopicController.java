package ru.alxstn.irbisnews.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alxstn.irbisnews.dto.NewsTopicDTO;
import ru.alxstn.irbisnews.dto.builder.NewsTopicDTOBuilder;
import ru.alxstn.irbisnews.repository.NewsTopicRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/topic")
public class NewsTopicController {

    private final NewsTopicRepository repository;
    private final NewsTopicDTOBuilder dtoBuilder;


    public NewsTopicController(NewsTopicRepository repository,
                               NewsTopicDTOBuilder dtoBuilder) {
        this.repository = repository;
        this.dtoBuilder = dtoBuilder;
    }

    @GetMapping
    public List<NewsTopicDTO> getAllTopics() {
        return repository.findAll().stream().map(dtoBuilder::fromNewsTopic).toList();
    }
}
