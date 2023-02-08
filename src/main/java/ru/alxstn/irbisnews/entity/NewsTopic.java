package ru.alxstn.irbisnews.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "topic")
public class NewsTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "source_id")
    private NewsSource source;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "newsTopic")
    private List<NewsEntry> news = new ArrayList<>();

    public NewsTopic() {
    }

    public NewsTopic(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public NewsSource getSource() {
        return source;
    }

    public void setSource(NewsSource newsSource) {
        this.source = newsSource;
    }

    public List<NewsEntry> getNews() {
        return news;
    }

    public void setNews(List<NewsEntry> news) {
        this.news = news;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsTopic newsTopic = (NewsTopic) o;

        if (!Objects.equals(id, newsTopic.id)) return false;
        if (!Objects.equals(title, newsTopic.title)) return false;
        if (!Objects.equals(source, newsTopic.source)) return false;
        return Objects.equals(news, newsTopic.news);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (news != null ? news.hashCode() : 0);
        return result;
    }
}
