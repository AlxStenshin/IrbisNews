package ru.alxstn.irbisnews.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "News Topic Not Found")
public class NewsTopicNotFoundException extends RuntimeException {
    public NewsTopicNotFoundException(String source) {
        super("News Topic Not Found: " + source);
    }
}
