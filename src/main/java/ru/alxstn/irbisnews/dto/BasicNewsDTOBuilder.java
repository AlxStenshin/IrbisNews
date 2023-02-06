package ru.alxstn.irbisnews.dto;

import org.springframework.stereotype.Component;
import ru.alxstn.irbisnews.entity.NewsEntry;

@Component
public class BasicNewsDTOBuilder implements NewsDTOBuilder {
    @Override
    public NewsDTO fromEntry(NewsEntry entry) {
        return new NewsDTO() {{
            setId(entry.getId());
            setContent(entry.getContent());
            setSource(entry.getNewsSource().getName());
            setTopic(entry.getNewsTopic().getTitle());
        }};
    }
}
