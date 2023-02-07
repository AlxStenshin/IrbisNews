package ru.alxstn.irbisnews.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alxstn.irbisnews.dto.NewsSourceDTO;
import ru.alxstn.irbisnews.service.NewsSourceService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/source")
public class NewsSourceController {

    private final NewsSourceService service;

    public NewsSourceController(NewsSourceService service) {
        this.service = service;
    }

    @GetMapping
    public List<NewsSourceDTO> getAllSources() {
        return service.findAllSources();
    }

}
