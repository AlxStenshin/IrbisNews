package ru.alxstn.irbisnews.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alxstn.irbisnews.entity.NewsSource;
import ru.alxstn.irbisnews.repository.NewsSourceRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/source/")
public class NewsSourceController {

    private final NewsSourceRepository repository;

    public NewsSourceController(NewsSourceRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<NewsSource> getAllSources() {
        return repository.findAll();
    }

}
