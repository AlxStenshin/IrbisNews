package ru.alxstn.irbisnews.task;

import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

@Component
public class ReportTaskExecutor {

    Logger logger = LogManager.getLogger(ReportTaskExecutor.class);
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
            logger.warn("Task Rejected: recreating executor now.");
            init();
            executorService.execute(runnable);
        }
    }
}