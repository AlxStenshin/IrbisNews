package ru.alxstn.irbisnews.dto.builder;

import org.springframework.stereotype.Component;
import ru.alxstn.irbisnews.dto.NewsSourceDTO;
import ru.alxstn.irbisnews.entity.NewsSource;

@Component
public class BasicNewsSourceDTOBuilder implements NewsSourceDTOBuilder {

    @Override
    public NewsSourceDTO fromNewsSource(NewsSource source) {
        return new NewsSourceDTO(
                source.getId(),
                source.getName());
    }
}
