package ru.alxstn.irbisnews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.alxstn.irbisnews.properties.AuthConfigurationProperties;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(AuthConfigurationProperties.class)
public class IrbisNewsApplication {
    public static void main(String[] args) {
        SpringApplication.run(IrbisNewsApplication.class, args);
    }

}
