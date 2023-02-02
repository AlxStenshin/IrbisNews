package ru.alxstn.irbisnews.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alxstn.irbisnews.entity.news.NewsEntry;
import ru.alxstn.irbisnews.repository.NewsRepository;


@RestController
@RequestMapping(value = "/api/v1/news")
public class NewsController {

    private final NewsRepository repository;

    public NewsController(NewsRepository repository) {
        this.repository = repository;
    }

    @RequestMapping()
    public Page<NewsEntry> getAll(@RequestParam(required = false) String source,
                                  @RequestParam(required = false) String topic,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "3") int count) {

        PageRequest pr = PageRequest.of(page, count);
        return repository.findAll(pr);
    }
}
