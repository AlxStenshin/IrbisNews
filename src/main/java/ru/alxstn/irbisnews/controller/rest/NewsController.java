package ru.alxstn.irbisnews.controller.rest;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alxstn.irbisnews.dto.NewsDTO;
import ru.alxstn.irbisnews.service.NewsService;


@RestController
@RequestMapping(value = "/api/v1/news")
public class NewsController {

    private final NewsService service;

    public NewsController(NewsService service) {
        this.service = service;
    }

    @GetMapping()
    public Page<NewsDTO> getNews(@RequestParam(required = false) String source,
                                 @RequestParam(required = false) String topic,
                                 @RequestParam(defaultValue = "0") Integer page,
                                 @RequestParam(defaultValue = "3") Integer count) {
        return service.findNews(source, topic, page, count);
    }
}
