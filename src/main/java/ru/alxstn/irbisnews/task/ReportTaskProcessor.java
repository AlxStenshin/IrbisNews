package ru.alxstn.irbisnews.task;

import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.alxstn.irbisnews.entity.NewsEntry;
import ru.alxstn.irbisnews.entity.NewsSource;
import ru.alxstn.irbisnews.exception.ReportFileException;
import ru.alxstn.irbisnews.dto.TopicReport;
import ru.alxstn.irbisnews.properties.ReportBuilderConfigurationProperties;
import ru.alxstn.irbisnews.repository.NewsRepository;
import ru.alxstn.irbisnews.utils.ReportFieldMappingStrategy;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportTaskProcessor {

    Logger logger = LogManager.getLogger(ReportTaskProcessor.class);
    private final NewsRepository repository;
    private final NewsSource source;
    private final ReportBuilderConfigurationProperties properties;


    public ReportTaskProcessor(NewsRepository repository,
                               NewsSource source,
                               ReportBuilderConfigurationProperties properties) {
        this.repository = repository;
        this.source = source;
        this.properties = properties;
    }

    public void executeTask() {
        logger.info("Fetching news for source: " + source.getName());

        List<NewsEntry> news = repository.findAllByNewsSource(source);
        Map<String, Long> report = new HashMap<>();
        for (NewsEntry n : news) {
            String title = n.getNewsTopic().getTitle();
            report.put(title, report.getOrDefault(title, 0L) + 1);
        }

        List<TopicReport> result = report.entrySet().stream()
                .map(e -> new TopicReport(e.getKey(), e.getValue()))
                .toList();

        Path targetFile = Path.of(buildFileName(source.getName())).toAbsolutePath();
        logger.info("Saving report for source '" + source.getName() + "'to CSV file: " + targetFile);

        try (Writer writer = new FileWriter(targetFile.toString())) {
            ReportFieldMappingStrategy<TopicReport> mappingStrategy = new ReportFieldMappingStrategy<>();
            mappingStrategy.setType(TopicReport.class);

            new StatefulBeanToCsvBuilder<TopicReport>(writer)
                    .withMappingStrategy(mappingStrategy)
                    .build()
                    .write(result);
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e) {
            throw new ReportFileException("Failed writing file: " + targetFile, e);
        }
    }

    private String buildFileName(String sourceName) {
        return new StringBuilder()
                .append(properties.getDirectory())
                .append(properties.isPrependTimestamp() ?
                        LocalDateTime.now((ZoneId.of("Europe/Moscow")))
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_hh-mm-ss")) + "_" :
                        "")
                .append(sourceName)
                .append(".csv")
                .toString();
    }
}
