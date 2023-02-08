package ru.alxstn.irbisnews.task;

import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.alxstn.irbisnews.entity.NewsSource;
import ru.alxstn.irbisnews.exception.ReportFileException;
import ru.alxstn.irbisnews.model.TopicReport;
import ru.alxstn.irbisnews.properties.ReportBuilderConfigurationProperties;
import ru.alxstn.irbisnews.utils.ReportFieldMappingStrategy;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReportTaskProcessor {

    Logger logger = LogManager.getLogger(ReportTaskProcessor.class);
    private final NewsSource source;
    private final ReportBuilderConfigurationProperties properties;


    public ReportTaskProcessor(NewsSource source,
                               ReportBuilderConfigurationProperties properties) {
        this.source = source;
        this.properties = properties;
    }

    public void executeTask() throws ReportFileException {
        logger.info("Building report for source: " + source.getName());

        var result = source.getTopics().stream()
                .map(t -> new TopicReport(t.getTitle(), (long) t.getNews().size()))
                .toList();

        saveToFile(buildFileName(source.getName()), result);
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

    private void saveToFile(String targetFileName, List<TopicReport> data) {
        Path targetFile = Path.of(targetFileName).toAbsolutePath();

        try (Writer writer = new FileWriter(targetFile.toString())) {
            ReportFieldMappingStrategy<TopicReport> mappingStrategy = new ReportFieldMappingStrategy<>();
            mappingStrategy.setType(TopicReport.class);

            new StatefulBeanToCsvBuilder<TopicReport>(writer)
                    .withMappingStrategy(mappingStrategy)
                    .build()
                    .write(data);

            logger.info("Source report '" + source.getName() + "'saved to CSV file: " + targetFile);

        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e) {
            throw new ReportFileException("Failed writing file: " + targetFile, e);
        }
    }
}
