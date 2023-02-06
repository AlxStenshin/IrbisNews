package ru.alxstn.irbisnews.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alxstn.irbisnews.entity.NewsTopic;
import ru.alxstn.irbisnews.repository.NewsTopicRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/topic")
public class NewsTopicController {

    private final NewsTopicRepository repository;

    public NewsTopicController(NewsTopicRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<NewsTopic> getAllTopics() {
        return repository.findAll();
    }
}
