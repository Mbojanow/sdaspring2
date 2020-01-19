package pl.sdacademy.springdemo.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pick {

  @JsonProperty("article_name")
  private String articleName;

  @JsonProperty("timestamp")
  private LocalDateTime timestamp;
}
