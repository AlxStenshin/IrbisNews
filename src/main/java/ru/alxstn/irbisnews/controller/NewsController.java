package ru.alxstn.irbisnews.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alxstn.irbisnews.entity.news.NewsEntry;
import ru.alxstn.irbisnews.service.NewsService;


@RestController
@RequestMapping(value = "/api/v1/news")
public class NewsController {

    private final NewsService service;

    public NewsController(NewsService service) {
        this.service = service;
    }

    @RequestMapping()
    public Page<NewsEntry> getNews(@RequestParam(required = false) String source,
                                   @RequestParam(required = false) String topic,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "1") int count) {
        // ToDo: handle exceptions
        return service.findNews(source, topic, page, count);
    }
}
