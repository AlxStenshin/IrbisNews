package ru.alxstn.irbisnews.entity.news;

import jakarta.persistence.*;

@Entity
@Table(name = "topic")
public class NewsTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    public NewsTopic() {
    }

    public NewsTopic(String title) {
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
