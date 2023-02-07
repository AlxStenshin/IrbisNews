package ru.alxstn.irbisnews.dto.builder;

import ru.alxstn.irbisnews.dto.NewsDTO;
import ru.alxstn.irbisnews.entity.NewsEntry;

public interface NewsDTOBuilder {
    NewsDTO fromEntry(NewsEntry entry);
}
