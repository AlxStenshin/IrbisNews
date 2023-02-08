package ru.alxstn.irbisnews.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.alxstn.irbisnews.dto.NewsDTO;
import ru.alxstn.irbisnews.dto.builder.NewsDTOBuilder;
import ru.alxstn.irbisnews.entity.NewsSource;
import ru.alxstn.irbisnews.entity.NewsTopic;
import ru.alxstn.irbisnews.exception.NewsSourceNotFoundException;
import ru.alxstn.irbisnews.exception.NewsTopicNotFoundException;
import ru.alxstn.irbisnews.repository.NewsRepository;
import ru.alxstn.irbisnews.repository.NewsSourceRepository;
import ru.alxstn.irbisnews.repository.NewsTopicRepository;

import java.util.Optional;

@Service
public class BasicNewsService implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsSourceRepository newsSourceRepository;
    private final NewsTopicRepository newsTopicRepository;
    private final NewsDTOBuilder dtoBuilder;

    public BasicNewsService(NewsRepository newsRepository,
                            NewsSourceRepository newsSourceRepository,
                            NewsTopicRepository newsTopicRepository,
                            NewsDTOBuilder dtoBuilder) {
        this.newsRepository = newsRepository;
        this.newsSourceRepository = newsSourceRepository;
        this.newsTopicRepository = newsTopicRepository;
        this.dtoBuilder = dtoBuilder;
    }

    @Override
    public Page<NewsDTO> findNews(String source, String topic,
                                  Integer page, Integer count) {

        PageRequest pr = PageRequest.of(Optional.ofNullable(page).orElse(0),
                Optional.ofNullable(count).orElse(3));
        var src = Optional.ofNullable(source);
        var top = Optional.ofNullable(topic);

        if (src.isEmpty() && top.isEmpty()) {
            return newsRepository.findAll(pr)
                    .map(dtoBuilder::fromEntry);

        } else
            return src.map(s -> newsRepository.findAllByNewsTopic_Source(findSource(s), pr)
                .map(dtoBuilder::fromEntry))
                .orElseGet(() -> newsRepository.findAllByNewsTopic(findTopic(top.get()), pr)
                .map(dtoBuilder::fromEntry));
    }

    private NewsSource findSource(String name) {
        return newsSourceRepository.findByName(name).
                orElseThrow(() -> new NewsSourceNotFoundException(name));
    }

    private NewsTopic findTopic(String title) {
        return newsTopicRepository.findByTitle(title).
                orElseThrow(() -> new NewsTopicNotFoundException(title));
    }

}
