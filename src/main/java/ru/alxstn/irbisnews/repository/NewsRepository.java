package ru.alxstn.irbisnews.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alxstn.irbisnews.entity.NewsEntry;
import ru.alxstn.irbisnews.entity.NewsSource;
import ru.alxstn.irbisnews.entity.NewsTopic;

import java.util.List;


@Repository
public interface NewsRepository extends JpaRepository<NewsEntry, Long> {

    Page<NewsEntry> findAllByNewsSource(NewsSource source, Pageable p);

    List<NewsEntry> findAllByNewsSource(NewsSource source);

    Page<NewsEntry> findAllByNewsTopic(NewsTopic topic, Pageable p);

    Page<NewsEntry> findAllByNewsSourceAndNewsTopic(NewsSource source, NewsTopic topic, Pageable p);

}
