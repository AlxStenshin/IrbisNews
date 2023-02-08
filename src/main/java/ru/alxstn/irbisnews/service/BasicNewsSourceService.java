package ru.alxstn.irbisnews.service;

import org.springframework.stereotype.Service;
import ru.alxstn.irbisnews.dto.NewsSourceDTO;
import ru.alxstn.irbisnews.dto.builder.NewsSourceDTOBuilder;
import ru.alxstn.irbisnews.repository.NewsSourceRepository;

import java.util.List;

@Service
public class BasicNewsSourceService implements NewsSourceService {

    private final NewsSourceRepository repository;
    private final NewsSourceDTOBuilder dtoBuilder;

    public BasicNewsSourceService(NewsSourceRepository repository, NewsSourceDTOBuilder dtoBuilder) {
        this.repository = repository;
        this.dtoBuilder = dtoBuilder;
    }

    @Override
    public List<NewsSourceDTO> findAllSources() {
        return repository.findAll().stream().map(dtoBuilder::fromNewsSource).toList();
    }

}
