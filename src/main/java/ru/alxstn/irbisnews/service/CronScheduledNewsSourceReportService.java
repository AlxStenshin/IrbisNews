package ru.alxstn.irbisnews.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.alxstn.irbisnews.entity.NewsSource;
import ru.alxstn.irbisnews.properties.ReportBuilderConfigurationProperties;
import ru.alxstn.irbisnews.repository.NewsSourceRepository;
import ru.alxstn.irbisnews.task.ReportTaskExecutor;
import ru.alxstn.irbisnews.task.ReportTaskProcessor;

import java.util.List;

@Service
public class CronScheduledNewsSourceReportService implements ReportService {

    Logger logger = LogManager.getLogger(CronScheduledNewsSourceReportService.class);

    private final ReportTaskExecutor taskExecutor;
    private final NewsSourceRepository sourceRepository;
    private final ReportBuilderConfigurationProperties properties;

    public CronScheduledNewsSourceReportService(ReportTaskExecutor taskExecutor,
                                                NewsSourceRepository sourceRepository,
                                                ReportBuilderConfigurationProperties properties) {
        this.taskExecutor = taskExecutor;
        this.sourceRepository = sourceRepository;
        this.properties = properties;
    }

    @Scheduled(cron = "${report.cron-expression}")
    @Override
    public void createReport() {
        List<NewsSource> sources = sourceRepository.findAll();
        logger.info("Started by cron. Fetching news sources: " + sources.size() + " entries found");

        sources.forEach(source -> taskExecutor.start(() ->
                new ReportTaskProcessor(source, properties).executeTask()));
    }

}
