package ru.alxstn.irbisnews.properties;

import jakarta.validation.constraints.NotEmpty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties("report")
public class ReportBuilderConfigurationProperties {

    Logger logger = LogManager.getLogger(ReportBuilderConfigurationProperties.class);

    @NotEmpty
    private String directory = ".";

    @NotEmpty
    private String cronExpression = "0 2 * * * *";

    private boolean prependTimestamp = false;

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
       if (CronExpression.isValidExpression(cronExpression))
            this.cronExpression = cronExpression;
        else {
            logger.warn("Provided report cron expression invalid, default one used: " + this.cronExpression);
        }
    }

    public boolean isPrependTimestamp() {
        return prependTimestamp;
    }

    public void setPrependTimestamp(boolean prependTimestamp) {
        this.prependTimestamp = prependTimestamp;
    }
}
