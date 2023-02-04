package ru.alxstn.irbisnews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alxstn.irbisnews.entity.NewsSource;

import java.util.Optional;

public interface NewsSourceRepository extends JpaRepository<NewsSource, Long> {
    Optional<NewsSource> findByName(String name);
}
