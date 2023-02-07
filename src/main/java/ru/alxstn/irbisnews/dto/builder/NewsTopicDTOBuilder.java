package ru.alxstn.irbisnews.dto.builder;

import ru.alxstn.irbisnews.dto.NewsTopicDTO;
import ru.alxstn.irbisnews.entity.NewsTopic;

public interface NewsTopicDTOBuilder {
    NewsTopicDTO fromNewsTopic(NewsTopic topic);
}
