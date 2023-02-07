package ru.alxstn.irbisnews.dto.builder;

import ru.alxstn.irbisnews.dto.NewsSourceDTO;
import ru.alxstn.irbisnews.entity.NewsSource;

public interface NewsSourceDTOBuilder {
    NewsSourceDTO fromNewsSource(NewsSource source);
}
