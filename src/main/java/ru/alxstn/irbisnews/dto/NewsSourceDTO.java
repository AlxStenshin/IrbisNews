package ru.alxstn.irbisnews.dto;

public class NewsSourceDTO {
    private String sourceName;
    private long id;

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
