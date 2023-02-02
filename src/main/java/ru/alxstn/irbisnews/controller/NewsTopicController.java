package ru.alxstn.irbisnews.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alxstn.irbisnews.entity.news.NewsTopic;
import ru.alxstn.irbisnews.repository.NewsTopicRepository;

import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/topic/")
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
