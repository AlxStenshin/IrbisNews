package ru.alxstn.irbisnews.dto.builder;

import org.springframework.stereotype.Component;
import ru.alxstn.irbisnews.dto.NewsDTO;
import ru.alxstn.irbisnews.entity.NewsEntry;

@Component
public class BasicNewsDTOBuilder implements NewsDTOBuilder {
    @Override
    public NewsDTO fromEntry(NewsEntry entry) {
        return new NewsDTO(
                entry.getId(),
                entry.getContent(),
                entry.getNewsSource().getName(),
                entry.getNewsTopic().getTitle());
    }
}
