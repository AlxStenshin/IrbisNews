package ru.alxstn.irbisnews.dto;

import ru.alxstn.irbisnews.entity.NewsEntry;

public interface NewsDTOBuilder {
    NewsDTO fromEntry(NewsEntry entry);

}
