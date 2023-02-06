package ru.alxstn.irbisnews.dto;

public class NewsDTO {
    private long id;
    private String content;
    private String source;
    private String topic;

    public NewsDTO() {
    }

    public NewsDTO(String content, String source, String topic) {
        this.content = content;
        this.source = source;
        this.topic = topic;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
