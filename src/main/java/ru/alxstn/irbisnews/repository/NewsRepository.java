package ru.alxstn.irbisnews.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alxstn.irbisnews.entity.NewsEntry;
import ru.alxstn.irbisnews.entity.NewsSource;
import ru.alxstn.irbisnews.entity.NewsTopic;


@Repository
public interface NewsRepository extends JpaRepository<NewsEntry, Long> {

    Page<NewsEntry> findAllByNewsTopic_Source(NewsSource newsSource, Pageable p);

    Page<NewsEntry> findAllByNewsTopic(NewsTopic topic, Pageable p);

}
