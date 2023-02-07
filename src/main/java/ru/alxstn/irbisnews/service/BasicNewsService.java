package ru.alxstn.irbisnews.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    Logger logger = LogManager.getLogger(BasicNewsService.class);

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
            logger.info("All News Request Received.");
            return newsRepository.findAll(pr)
                    .map(dtoBuilder::fromEntry);

        } else if (src.isPresent() && top.isPresent()) {
            logger.info("News By Source And Topic Request Received.");
            return newsRepository.findAllByNewsSourceAndNewsTopic(
                            findSource(src.get()), findTopic(top.get()), pr)
                    .map(dtoBuilder::fromEntry);

        } else if (src.isPresent()) {
            logger.info("News By Source Request Received.");
            return newsRepository.findAllByNewsSource(findSource(src.get()), pr)
                    .map(dtoBuilder::fromEntry);

        } else {
            logger.info("News By Topic Request Received.");
            return newsRepository.findAllByNewsTopic(findTopic(top.get()), pr)
                    .map(dtoBuilder::fromEntry);
        }
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
