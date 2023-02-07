package ru.alxstn.irbisnews.dto;

public class NewsTopicDTO {
    private String topicTitle;
    private long id;

    public NewsTopicDTO() {
    }

    public NewsTopicDTO(long id, String topicTitle) {
        this.topicTitle = topicTitle;
        this.id = id;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
