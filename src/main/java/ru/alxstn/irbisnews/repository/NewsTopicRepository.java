package ru.alxstn.irbisnews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alxstn.irbisnews.entity.NewsTopic;

import java.util.Optional;

@Repository
public interface NewsTopicRepository extends JpaRepository<NewsTopic, Long> {
    Optional<NewsTopic> findByTitle(String title);
}
