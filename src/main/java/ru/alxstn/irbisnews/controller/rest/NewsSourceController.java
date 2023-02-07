package ru.alxstn.irbisnews.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alxstn.irbisnews.dto.NewsSourceDTO;
import ru.alxstn.irbisnews.dto.builder.NewsSourceDTOBuilder;
import ru.alxstn.irbisnews.repository.NewsSourceRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/source")
public class NewsSourceController {

    private final NewsSourceRepository repository;
    private final NewsSourceDTOBuilder dtoBuilder;

    public NewsSourceController(NewsSourceRepository repository,
                                NewsSourceDTOBuilder dtoBuilder) {
        this.repository = repository;
        this.dtoBuilder = dtoBuilder;
    }

    @GetMapping
    public List<NewsSourceDTO> getAllSources() {
        return repository.findAll().stream().map(dtoBuilder::fromNewsSource).toList();
    }

}
