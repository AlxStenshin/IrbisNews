package ru.alxstn.irbisnews.entity.news;

import jakarta.persistence.*;

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
}
