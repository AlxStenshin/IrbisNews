package ru.alxstn.irbisnews.exception;

public class NewsTopicNotFoundException extends RuntimeException {
    public NewsTopicNotFoundException(String source) {
        super("News Topic Not Found: " + source);
    }
}
