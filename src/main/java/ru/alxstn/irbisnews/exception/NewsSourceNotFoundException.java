package ru.alxstn.irbisnews.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "News Source Not Found")
public class NewsSourceNotFoundException extends RuntimeException {
    public NewsSourceNotFoundException(String source) {
        super("News Source Not Found: " + source);
    }
}
