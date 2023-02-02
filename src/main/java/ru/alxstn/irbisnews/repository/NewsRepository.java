package ru.alxstn.irbisnews.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alxstn.irbisnews.entity.news.NewsEntry;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntry, Long> {
}
