package ru.alxstn.irbisnews.exception;

public class NewsSourceNotFoundException extends RuntimeException {
    public NewsSourceNotFoundException(String source) {
        super("News Source Not Found: " + source);
    }
}
