package ru.alxstn.irbisnews.dto.builder;

import org.springframework.stereotype.Component;
import ru.alxstn.irbisnews.dto.NewsTopicDTO;
import ru.alxstn.irbisnews.entity.NewsTopic;

@Component
public class BasicNewsTopicDTOBuilder implements NewsTopicDTOBuilder {
    @Override
    public NewsTopicDTO fromNewsTopic(NewsTopic topic) {
        return new NewsTopicDTO() {{
            setId(topic.getId());
            setTopicTitle(topic.getTitle());
        }};
    }
}
