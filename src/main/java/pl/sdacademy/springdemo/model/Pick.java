package pl.sdacademy.springdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pick {
    @JsonIgnore
    private Long pickerId;

    @JsonProperty("article_name")
    private String articleName;

    @JsonProperty("timestamp")
    private LocalDateTime timestamp;
}
