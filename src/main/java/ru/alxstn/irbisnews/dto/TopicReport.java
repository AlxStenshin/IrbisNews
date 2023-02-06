package ru.alxstn.irbisnews.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class TopicReport {

    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "Тема")
    private String topicName;

    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "Количество новостей")
    private Long recordsCount;

    public TopicReport(String topicName, Long recordsCount) {
        this.topicName = topicName;
        this.recordsCount = recordsCount;
    }

    @Override
    public String toString() {
        return topicName + ": " + recordsCount;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Long getRecordsCount() {
        return recordsCount;
    }

    public void setRecordsCount(Long recordsCount) {
        this.recordsCount = recordsCount;
    }
}
