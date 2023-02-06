package ru.alxstn.irbisnews.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.alxstn.irbisnews.dto.NewsDTO;
import ru.alxstn.irbisnews.dto.NewsDTOBuilder;
import ru.alxstn.irbisnews.entity.NewsSource;
import ru.alxstn.irbisnews.entity.NewsTopic;
import ru.alxstn.irbisnews.exception.NewsSourceNotFoundException;
import ru.alxstn.irbisnews.exception.NewsTopicNotFoundException;
import ru.alxstn.irbisnews.repository.NewsRepository;
import ru.alxstn.irbisnews.repository.NewsSourceRepository;
import ru.alxstn.irbisnews.repository.NewsTopicRepository;

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
    public Page<NewsDTO> findNews(String sourceName, String topicTitle, int page, int count) {
        PageRequest pr = PageRequest.of(page, count);
        boolean sourceProvided = sourceName != null && !sourceName.isBlank();
        boolean topicProvided = topicTitle != null && !topicTitle.isBlank();

        if (!sourceProvided && !topicProvided) {
            return newsRepository.findAll(pr)
                    .map(dtoBuilder::fromEntry);

        } else if (sourceProvided && topicProvided) {
            return newsRepository.findAllByNewsSourceAndNewsTopic(
                    findSource(sourceName), findTopic(topicTitle), pr)
                    .map(dtoBuilder::fromEntry);

        } else if (sourceProvided) {
            return newsRepository.findAllByNewsSource(findSource(sourceName), pr)
                    .map(dtoBuilder::fromEntry);

        } else {
            return newsRepository.findAllByNewsTopic(findTopic(topicTitle), pr)
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
