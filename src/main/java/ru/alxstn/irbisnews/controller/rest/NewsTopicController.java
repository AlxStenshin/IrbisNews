package ru.alxstn.irbisnews.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alxstn.irbisnews.dto.NewsTopicDTO;
import ru.alxstn.irbisnews.service.NewsTopicService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/topic")
public class NewsTopicController {

private final NewsTopicService service;

    public NewsTopicController(NewsTopicService service) {
        this.service = service;
    }

    @GetMapping
    public List<NewsTopicDTO> getAllTopics() {
        return service.findAllTopics();
    }
}
