package pl.sdacademy.springdemo.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@ConfigurationProperties(prefix = "pick.processing")
@Configuration
@Data
public class StreamProcessorConfiguration {

    private int maxEventsNum;
    private Duration maxProcessingTime;
}
