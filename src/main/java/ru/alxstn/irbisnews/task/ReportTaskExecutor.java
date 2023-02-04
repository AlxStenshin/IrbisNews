package ru.alxstn.irbisnews.task;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

@Component
public class ReportTaskExecutor {

    private ExecutorService executorService;

    @PostConstruct
    private void init() {
        int threadsNumber = 4;
        executorService = Executors.newFixedThreadPool(threadsNumber);
    }

    public void start(Runnable runnable) {
        try {
            executorService.execute(runnable);
        } catch (RejectedExecutionException e) {
            init();
            executorService.execute(runnable);
        }
    }
}