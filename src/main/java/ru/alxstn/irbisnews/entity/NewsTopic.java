package ru.alxstn.irbisnews.entity;

import jakarta.persistence.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsTopic newsTopic = (NewsTopic) o;

        return Objects.equals(title, newsTopic.title);
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }
}
