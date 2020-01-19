package pl.sdacademy.springdemo.configuration;


import static java.util.Objects.nonNull;

import java.time.Duration;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ConfigurationProperties(prefix = "events.processing")
@Configuration
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class StreamProcessorConfiguration {

  @Min(value = 1, message = "maxEventsNum has to be positive")
  private int maxEventsNum;

  private Duration maxProcessingTime;

  @AssertTrue
  public boolean isProcessingTimeValid() {
    return nonNull(maxProcessingTime) && maxProcessingTime.getSeconds() > 0;
  }
}
