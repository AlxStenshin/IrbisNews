package ru.alxstn.irbisnews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alxstn.irbisnews.entity.news.NewsSource;

public interface NewsSourceRepository extends JpaRepository<NewsSource, Long> {
}
