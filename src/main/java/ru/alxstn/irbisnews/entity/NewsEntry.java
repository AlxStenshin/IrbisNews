package ru.alxstn.irbisnews.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "news")
public class NewsEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "source_id", nullable = false)
    private NewsSource newsSource;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id", nullable = false)
    private NewsTopic newsTopic;

    @Column(name = "content")
    private String content;

    public NewsEntry() {
    }

    public NewsEntry(NewsSource newsSource, NewsTopic newsTopic, String content) {
        this.newsSource = newsSource;
        this.newsTopic = newsTopic;
        this.content = content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public NewsSource getNewsSource() {
        return newsSource;
    }

    public NewsTopic getNewsTopic() {
        return newsTopic;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsEntry newsEntry = (NewsEntry) o;

        if (!Objects.equals(newsSource, newsEntry.newsSource)) return false;
        if (!Objects.equals(newsTopic, newsEntry.newsTopic)) return false;
        return Objects.equals(content, newsEntry.content);
    }

    @Override
    public int hashCode() {
        int result = newsSource != null ? newsSource.hashCode() : 0;
        result = 31 * result + (newsTopic != null ? newsTopic.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
